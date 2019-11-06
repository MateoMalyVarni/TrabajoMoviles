package com.example.parcialmoviles

import android.app.IntentService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_actividad_servicios.*
import java.nio.channels.InterruptedByTimeoutException

class ActividadServicios : AppCompatActivity(){

    val TAG = "Actividad Servicios"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_servicios)

        var enteroRecibido = -1;
        var intentoMensajero = 0;

        val intentoPrimerServicio: EjemploIntentService = EjemploIntentService()

        val receptor = object :  BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d(TAG, "Inicia recepcion respuesta")
                enteroRecibido = intent!!.getIntExtra("Iteracion",-1)
                intentoMensajero = intent!!.getIntExtra("Mensajero", 0)
                if (intentoMensajero == 1)
                    texto_intent_service.setText("Iteración: $enteroRecibido")
                Log.d(TAG, "Finaliza recepción respuesta")
            }
        }
        registerReceiver(receptor, IntentFilter("Contar iteraciones"))
        Log.d(TAG,"El receptor fue registrado")
        boton_intent_service.setOnClickListener{
            var intentoActividad = Intent(this,EjemploIntentService::class.java)
            val cantidadDeIteraciones = 3
            for (i in 0..cantidadDeIteraciones){
                intentoActividad.putExtra("Iteracion",i)
                Log.d(TAG,"Envio a IntentService")
                startService(intentoActividad)
            }
        }
    }


}
