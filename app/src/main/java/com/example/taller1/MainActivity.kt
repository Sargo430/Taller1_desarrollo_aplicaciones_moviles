package com.example.taller1

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnSumaRapida = findViewById<Button>(R.id.btnSumaRapida)
        val btnDado = findViewById<Button>(R.id.btnAdivinarDados)
        btnSumaRapida.setOnClickListener {
            cambiarSumaRapida()
        }
        btnDado.setOnClickListener {
            cambiarAdivinarDado()
        }
    }

    private fun cambiarSumaRapida() {
        val intent =  Intent(this,SumaRapida::class.java)
        startActivity(intent)
    }
    private fun cambiarAdivinarDado() {
        val intent =  Intent(this,AdivinarDado::class.java)
        startActivity(intent)
    }
}