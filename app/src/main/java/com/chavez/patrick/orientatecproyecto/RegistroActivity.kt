package com.chavez.patrick.orientatecproyecto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val registerButton: Button = findViewById(R.id.boton_register)
        registerButton.setOnClickListener {
            // Navigate to InicioScreen (another activity)
            val intent = Intent(this, BienvenidaActivity::class.java)
            startActivity(intent)
        }
    }
}
