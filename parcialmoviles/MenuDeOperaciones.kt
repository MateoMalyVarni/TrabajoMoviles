package com.example.parcialmoviles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_actividad_menu_operaciones.*

class MenuDeOperaciones : AppCompatActivity() {

    val idOperacion = 1
    //var textoSalida:String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_de_operaciones)

        boton_suma.setOnClickListener{
            startActivityForResult(Intent(applicationContext,LecturaDeOperandos::class.java).putExtra("Operacion",'+'),idOperacion)
        }

        boton_resta.setOnClickListener{
            startActivityForResult(Intent(applicationContext,LecturaDeOperandos::class.java).putExtra("Operacion",'-'),idOperacion)
        }

        boton_multiplicacion.setOnClickListener{
            startActivityForResult(Intent(applicationContext,LecturaDeOperandos::class.java).putExtra("Operacion",'*'),idOperacion)
        }

        boton_division.setOnClickListener {
            startActivityForResult(
                Intent(applicationContext, LecturaDeOperandos::class.java).putExtra("Operacion", '/'), idOperacion)
        }

        if (savedInstanceState != null) {
            val salida: String? = savedInstanceState.getString("Resultado")
            resultado.setText(salida)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == idOperacion && resultCode == Activity.RESULT_OK){
            val numeroResultado = data?.getIntExtra("Resultado", 22)
            val textoSalida = "El resultado es: $numeroResultado"
            resultado.setText(textoSalida)

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("Resultado", resultado.text.toString())
    }
}
