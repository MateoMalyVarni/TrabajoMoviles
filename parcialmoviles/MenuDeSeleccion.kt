package com.example.parcialmoviles

import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.parcialmoviles.R.layout.activity_menu_de_seleccion
import kotlinx.android.synthetic.main.activity_menu_de_seleccion.*

class MenuDeSeleccion : AppCompatActivity() {

    fun mensajeSaludo() {
        val constructorDeDialogos: AlertDialog.Builder = AlertDialog.Builder(this)
        constructorDeDialogos.setTitle("¡Hola!")
            .setMessage("Bienvenido a la aplicación desarrollada durante la cursada de Introducción a la Programación de Dispositivos Móviles")
            .setPositiveButton(
                "Comenzar",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        constructorDeDialogos.create().show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_menu_de_seleccion)

        mensajeSaludo()
        val botonActividad1: Button = findViewById(R.id.boton_actividad_1)
        botonActividad1.setOnClickListener {
            startActivity(Intent(applicationContext, ActividadImpresion::class.java))
            /** De donde parto a donde quiero llegar
            (accion que ejecuto)*/
            /** De donde parto a donde quiero llegar
            (accion que ejecuto)*/
        }
        val botonActividad2: Button = findViewById(R.id.boton_actividad_2)
        botonActividad2.setOnClickListener {
            startActivity(Intent(applicationContext, MenuDeOperaciones::class.java))
        }

        boton_actividad_3.setOnClickListener({
            startActivity(Intent(applicationContext,Cronometro::class.java))
        })

        boton_actividad_4.setOnClickListener({
            startActivity(Intent(applicationContext,ActividadServicios::class.java))
        })
    }

}
