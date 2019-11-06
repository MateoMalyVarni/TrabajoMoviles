package com.example.parcialmoviles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.widget.Toast
import com.example.parcialmoviles.R.layout.activity_lectura_de_operandos
import kotlinx.android.synthetic.main.activity_lectura_de_operandos.*

class LecturaDeOperandos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_lectura_de_operandos)

        boton_calculo.setOnClickListener() {
            if (primer_numero.text.toString() == "" || segundo_numero.text.toString() == ""){
                Toast.makeText(applicationContext,"Operandos sin asignar. Por favor, complete un valor.",Toast.LENGTH_SHORT).show()
            }
            else{
                val operandoUno: Int = Integer.parseInt(primer_numero.text.toString())
                val operandoDos: Int = Integer.parseInt(segundo_numero.text.toString())
                val operacion = getIntent().getCharExtra("Operacion", '0')
                if (operacion == '/' && operandoDos == 0)
                    Toast.makeText(applicationContext,"No se puede dividir por 0!",Toast.LENGTH_SHORT).show()
                else{
                    val intencionRetorno = Intent()
                    intencionRetorno.putExtra("Resultado", realizarOperacion(operacion).invoke(operandoUno, operandoDos))
                    setResult(Activity.RESULT_OK, intencionRetorno)
                    finish()
                    }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    fun realizarOperacion(charOperator: Char): (Int, Int) -> Int {
        return when (charOperator) {
            '+' -> { a, b -> a + b }
            '-' -> { a, b -> a - b }
            '/' -> { a, b -> a / b }
            '*' -> { a, b -> a * b }
            else -> throw Exception("That's not a supported operator")
        }
    }
}


