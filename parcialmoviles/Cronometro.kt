package com.example.parcialmoviles

import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cronometro.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock


class Cronometro : AppCompatActivity() {

    inner class CronometroSegundoPlano : AsyncTask<Integer,Int,Int>(){

        override fun doInBackground(vararg params: Integer?): Int {
            while (active){
                lock.withLock {
                    condition.await(step, TimeUnit.SECONDS)
                    if (active)
                        counter++
                    Log.d(TAG,"Segundos"+counter)
                    publishProgress(counter)
                }
            }
            return counter
        }


        override fun onProgressUpdate(vararg values: Int?) { //NO EJECUTAR. SE ROMPE. Android lo ejecuta solo.
            super.onProgressUpdate(*values)
            counterText.text = counter.toString()
        }
    }

    var step:Long = 0
    val TAG = "MainActivity"
    var active = false
    var counter = 0
    val nombreArchivo = "Preferencias"
    var preferencias : SharedPreferences? = null

    val lock = ReentrantLock()
    val condition = lock.newCondition()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cronometro)

        stop.isEnabled = false

        preferencias = applicationContext.getSharedPreferences(nombreArchivo, Context.MODE_PRIVATE)
        counter = preferencias!!.getInt("Contador",-1)  //Recupero el valor
        if (counter != -1)
            counterText.text = counter.toString()

        start.setOnClickListener{
            active = true
            stop.isEnabled = true
            if (stepCounter.text.toString() != "")
                step = stepCounter.text.toString().toLong()
            else{
                step = 1
                Toast.makeText(applicationContext, "No ha ingresado un valor válido. Se utilizará como valor 1",Toast.LENGTH_SHORT).show()
            }
            var activador : CronometroSegundoPlano = CronometroSegundoPlano()
            activador.execute()
        }

        stop.setOnClickListener{
            Log.d(TAG,"Stop clicked")
            active = false
            stop.isEnabled = false
        }

        reset.setOnClickListener({
            Log.d(TAG, "Reseteo de Contador")
            counter = 0
            counterText.text = counter.toString()
        })
    }

    override fun onPause() {
        super.onPause()

        val editor: SharedPreferences.Editor = preferencias!!.edit()
        editor.putInt("Contador",counter)
        editor.apply()
    }
}
