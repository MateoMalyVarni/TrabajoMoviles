package com.example.parcialmoviles

import android.app.IntentService
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_actividad_servicios.*
import java.nio.channels.InterruptedByTimeoutException

class ActividadServicios : AppCompatActivity(){

    val TAG = "Actividad Servicios"

    var servicioG : EjemploBoundService = EjemploBoundService()

    var booleano:Boolean = false

    val conexion : ServiceConnection = object : ServiceConnection{

        override fun onServiceDisconnected(name: ComponentName?) {
            booleano = false
        }

        override fun onServiceConnected(nombreClase:ComponentName,servicio: IBinder){
            val vinculador : EjemploBoundService.vinculadorLocal = servicio as EjemploBoundService.vinculadorLocal
            servicioG = vinculador.getServicio()
            booleano = true

        }

    }

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
                if (intentoMensajero == 2)
                    texto_service.setText("Iteración: $enteroRecibido")
                Log.d(TAG, "Finaliza recepción respuesta")
            }
        }
        registerReceiver(receptor, IntentFilter("Contar iteraciones"))
        Log.d(TAG,"El receptor fue registrado")
        boton_intent_service.setOnClickListener{
            var intentoActividad = Intent(this,EjemploIntentService::class.java)
            val cantidadDeIteraciones = 10
            for (i in 0..cantidadDeIteraciones){
                intentoActividad.putExtra("Iteracion",i)
                Log.d(TAG,"Envio a IntentService")
                startService(intentoActividad)
            }
        }
        boton_service.setOnClickListener{
            var intentoActividad = Intent(this,EjemploServicio::class.java)
            val cantidadDeIteraciones = 10
            for (i in 0..cantidadDeIteraciones){
                intentoActividad.putExtra("Iteracion",i)
                Log.d(TAG,"Envio a Service")
                startService(intentoActividad)
            }
        }
        boton_bound_service.setOnClickListener {
            onButtonCLick(View(this))
        }
    }

    fun onButtonCLick(v:View){
        if (booleano){
            val numero : Int = servicioG.getRandom()
            texto_bound.setText("El número fue: $numero")
        }
    }

    override fun onStart() {
        super.onStart()
        var intencion :Intent = Intent(this, EjemploBoundService::class.java)
        bindService(intencion,conexion,Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (booleano){
            unbindService(conexion)
            booleano = false
        }
    }



}
