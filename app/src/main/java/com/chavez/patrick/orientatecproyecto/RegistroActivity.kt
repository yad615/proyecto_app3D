package com.chavez.patrick.orientatecproyecto

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Paint
import okhttp3.*
import java.io.IOException

class RegistroActivity : AppCompatActivity() {
    private lateinit var fullNameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var startButton: Button
    private lateinit var eyeIcon: ImageView
    private lateinit var loginText: TextView

    private val client = OkHttpClient()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        fullNameInput = findViewById(R.id.fullNameInput)
        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        startButton = findViewById(R.id.startButton)
        eyeIcon = findViewById(R.id.eyeIcon)
        loginText = findViewById(R.id.loginText)

        // Agrega subrayado al TextView
        loginText.paintFlags = loginText.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        startButton.setOnClickListener {
            registerUser() // Validar y registrar al usuario
        }

        // Configura el listener para el ícono de ojo
        eyeIcon.setOnClickListener {
            togglePasswordVisibility()
        }

        // Configura el listener para el TextView de inicio de sesión
        loginText.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser() {
        val fullName = fullNameInput.text.toString()
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()

        // Validar que los campos no estén vacíos
        if (fullName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            // Aquí puedes agregar la lógica para el registro en una base de datos
            val requestBody = """
                {
                    "fullName": "$fullName",
                    "email": "$email",
                    "password": "$password"
                }
            """.trimIndent()

            val request = Request.Builder()
                .url("http://tu-servidor.com/api/usuarios") // Cambia por la URL de tu servidor
                .post(RequestBody.create(MediaType.parse("application/json"), requestBody))
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        runOnUiThread {
                            Toast.makeText(this@RegistroActivity, "Usuario registrado: $fullName", Toast.LENGTH_SHORT).show()
                            navigateToInicioActivity() // Navegar a la nueva vista
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@RegistroActivity, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        } else {
            Toast.makeText(this, "Por favor completa todos los campos.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun togglePasswordVisibility() {
        // Verifica el tipo de entrada actual del campo de contraseña
        if (passwordInput.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            // Oculta la contraseña
            passwordInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            eyeIcon.setImageResource(R.drawable.cerrar_ojo) // Cambia al ícono de ojo cerrado
        } else {
            // Muestra la contraseña
            passwordInput.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            eyeIcon.setImageResource(R.drawable.ojo) // Cambia al ícono de ojo abierto
        }
        passwordInput.setSelection(passwordInput.text.length) // Mantener el cursor al final
    }

    private fun navigateToInicioActivity() {
        val intent = Intent(this, InicioActivity::class.java) // Crear el intent
        startActivity(intent) // Iniciar la nueva actividad
    }
}
