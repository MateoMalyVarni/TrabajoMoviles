package com.example.parcialmoviles

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import java.lang.Integer.parseInt
import java.lang.Process


class EjemploServicio : Service (){

    var loopDeServicio:Looper? = null
    var manipuladorDeServicio:ServiceHandler? = null
    val TAG = "Ejemplo Servicio"

    inner class ServiceHandler (loop:Looper) : Handler(){

        val TAG = "Handler"

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            try {
                Log.d(TAG, "Ingresando Mensaje")
                var iteracionActual = parseInt(msg.obj.toString())
                Log.d(TAG,"Iteracion en progreso: $iteracionActual")
                var intentoSalida = Intent("Contar iteraciones")
                intentoSalida.putExtra("Mensajero", 2)
                intentoSalida.putExtra("Iteracion", iteracionActual)
                Log.d(TAG, "Envio de intento")
                sendBroadcast(intentoSalida)
                Thread.sleep(1000)
            }
            catch (e : InterruptedException){
                Thread.currentThread().interrupt()
            }
            stopSelf()
        }
    }

    override fun onCreate(){
        Log.d(TAG,"Inicializacion de Thread")
        val thread : HandlerThread = HandlerThread("Thread de Trabajo", android.os.Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()
            loopDeServicio = looper
            manipuladorDeServicio = ServiceHandler(loopDeServicio!!)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG,"Iniciando la ejecucion")
        val mensaje : Message = manipuladorDeServicio!!.obtainMessage()
        mensaje.arg1 = startId
        mensaje.obj = intent?.getIntExtra("Iteracion",-1)
        manipuladorDeServicio?.sendMessage(mensaje)
        stopSelf()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}