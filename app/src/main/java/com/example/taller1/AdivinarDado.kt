package com.example.taller1

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AdivinarDado : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_adivinar_dado)
        val  btnEnter = findViewById<Button>(R.id.btnEnter)
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
        val mp = MediaPlayer.create(this, R.raw.dice)
        temporizador.start()
        btnEnter.setOnClickListener {
            try {
                mp.start()
                intentos+=1
                if(generarDado()){

                    puntos+=5
                    txtPuntos.text="Puntos: $puntos"
                }else{

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
    private fun generarDado(): Boolean {
        val dado = (1..6).random().toInt()
        val imgDado = findViewById<ImageView>(R.id.imgDado)
        when(dado){
            1 -> imgDado.setImageResource(R.drawable.dado1)
            2 -> imgDado.setImageResource(R.drawable.dado2)
            3 -> imgDado.setImageResource(R.drawable.dado3)
            4 -> imgDado.setImageResource(R.drawable.dado4)
            5 -> imgDado.setImageResource(R.drawable.dado5)
            6 -> imgDado.setImageResource(R.drawable.dado6)
        }
        val respuesta = findViewById<EditText>(R.id.txtRespuesta).text.toString().toInt()
        if(respuesta == dado){
            return true
        }
        else{
            return false
        }
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