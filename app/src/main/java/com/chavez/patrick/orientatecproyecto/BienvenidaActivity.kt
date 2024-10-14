package com.chavez.patrick.orientatecproyecto

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BienvenidaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bienvenida)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Crear un degradado programáticamente para el botón
        val gradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(0xFFFF5733.toInt(), 0xFFC70039.toInt()))
        gradient.cornerRadius = 8f // Ajustar el radio de las esquinas

        // Obtener el botón y establecer el fondo
        val startButton = findViewById<Button>(R.id.startButton)
        startButton.background = gradient
    }


    // Método para navegar a RegistroActivity
    fun goToRegistroActivity(view: View) {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }
}
