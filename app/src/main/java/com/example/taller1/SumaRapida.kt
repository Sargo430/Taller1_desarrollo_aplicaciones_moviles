package com.example.taller1

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class SumaRapida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_suma_rapida)
        val  btnEnter = findViewById<Button>(R.id.btnEnter)
        val n1 = findViewById<TextView>(R.id.txtN1)
        val n2 = findViewById<TextView>(R.id.txtN2)
        val txtPuntos = findViewById<TextView>(R.id.txtPuntos)
        var puntos =0
        var vidas = 5
        var intentos = 0
        val txtVidas = findViewById<TextView>(R.id.txtVidas)
        val txtTiempo = findViewById<TextView>(R.id.txtTiempo)
        val temporizador = object : CountDownTimer(10_000,1_000){
            override fun onTick(millisUntilFinished: Long) {
                var tiempoEnSegundos = (millisUntilFinished/1000).toInt()
                txtTiempo.text = "Tiempo: $tiempoEnSegundos"
            }

            override fun onFinish() {
                dialogogameOver()
            }

        }
        txtVidas.text= "Vidas: $vidas"
        generarNumeros(n1,n2,intentos)
        temporizador.start()
        btnEnter.setOnClickListener {
            try {
                val resultado = n1.text.toString().toInt() + n2.text.toString().toInt()
                intentos+=1
                if(ingresarRespuesta(resultado)){
                    generarNumeros(n1,n2,intentos)
                    puntos+=intentos
                    txtPuntos.text="Puntos: $puntos"
                }else{
                    generarNumeros(n1,n2,intentos)
                    vidas -=1
                    txtVidas.text= "Vidas: $vidas"
                    if(vidas==0){
                        dialogogameOver()
                    }
                }
                temporizador.cancel()
                temporizador.start()
            }catch (e: Exception){
                Toast.makeText(this, "Ingresa un numero valido", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun ingresarRespuesta(resultado: Int): Boolean {

        val respuesta = findViewById<EditText>(R.id.txtRespuesta).text.toString().toInt()
        if(respuesta == resultado){
            return true
        }
        else{
            return false
        }
    }
    private fun generarNumeros(n1: TextView, n2: TextView,intentos: Int) {
        val nMax = 10 + intentos
        var nMin=1
        if (intentos>2){
            nMin= 1 + (intentos/2).toInt()
        }
        n1.text = (nMin..nMax).random().toString()
        n2.text = (nMin..nMax).random().toString()
    }

    fun dialogogameOver(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage("¿Quieres Reiniciar?")
            .setTitle("Perdiste")
            .setPositiveButton("Reiniciar") { dialog, which ->
                recreate()
            }
            .setNegativeButton("Salir") { dialog, which ->
                val intent =  Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}
