package com.example.parcialmoviles
import android.app.IntentService
import android.content.Intent
import android.util.Log

class EjemploIntentService () : IntentService("EjemploIntentService"){

    val TAG = "Ejecucion de IntentService"



    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG,"Ingresando a IntentService")
        var iteracionActual = intent!!.getIntExtra("Iteracion", -1)
        var intentoSalida = Intent("Contar iteraciones")
        intentoSalida.putExtra("Mensajero", 1)
        intentoSalida.putExtra("Iteracion",iteracionActual)
        Log.d(TAG,"Envio de intento")
        sendBroadcast(intentoSalida)
        Thread.sleep(1000)
    }
}
