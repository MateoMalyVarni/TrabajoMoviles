package com.example.parcialmoviles

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

class EjemploBoundService : Service() {

    val vinculador : IBinder = vinculadorLocal()
    val generador : Random = Random()

    inner class vinculadorLocal : Binder(){

        fun getServicio() : EjemploBoundService{
            return this@EjemploBoundService
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return vinculador
    }

    fun getRandom():Int{
        return generador.nextInt(100)
    }
}
