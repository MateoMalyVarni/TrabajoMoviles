package com.example.parcialmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.R.id.button1
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.View.OnClickListener
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.LayoutInflater
import android.widget.*
import com.example.parcialmoviles.R.layout.activity_actividad_impresion


class ActividadImpresion : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_actividad_impresion)

        var cantidadDeToques = 0;
        val botonContador :Button = findViewById(R.id.boton_contador)
        botonContador.setOnClickListener {
            Toast.makeText(applicationContext,"Ha tocado el boton ${cantidadDeToques+1} veces",Toast.LENGTH_SHORT).show()
            cantidadDeToques += 1
        }
        val botonReseteo :Button = findViewById(R.id.boton_reseteo)
        botonReseteo.setOnClickListener {
            Toast.makeText(applicationContext,"Contador reiniciado",Toast.LENGTH_SHORT).show()
            cantidadDeToques = 0
        }
    }
}


