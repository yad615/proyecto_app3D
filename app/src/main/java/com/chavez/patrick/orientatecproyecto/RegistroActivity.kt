package com.chavez.patrick.orientatecproyecto

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {
    private lateinit var fullNameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var startButton: Button
    private lateinit var eyeIcon: ImageView // Declara el ícono de ojo

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        fullNameInput = findViewById(R.id.fullNameInput)
        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        startButton = findViewById(R.id.startButton)
        eyeIcon = findViewById(R.id.eyeIcon) // Inicializa el ícono de ojo

        startButton.setOnClickListener {
            registerUser()
        }

        // Configura el listener para el ícono de ojo
        eyeIcon.setOnClickListener {
            togglePasswordVisibility()
        }
    }

    private fun registerUser() {
        val fullName = fullNameInput.text.toString()
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()

        // Aquí puedes agregar la lógica para guardar el usuario
        if (fullName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            // Aquí puedes agregar la lógica para el registro en una base de datos
            Toast.makeText(this, "Usuario registrado: $fullName", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Por favor completa todos los campos.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun togglePasswordVisibility() {
        // Verifica el tipo de entrada actual del campo de contraseña
        if (passwordInput.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            // Oculta la contraseña
            passwordInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            eyeIcon.setImageResource(R.drawable.ojo) // Cambia al ícono de ojo cerrado
        } else {
            // Muestra la contraseña
            passwordInput.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            eyeIcon.setImageResource(R.drawable.ojo) // Cambia al ícono de ojo abierto
        }
        passwordInput.setSelection(passwordInput.text.length) // Mantener el cursor al final
    }
}
