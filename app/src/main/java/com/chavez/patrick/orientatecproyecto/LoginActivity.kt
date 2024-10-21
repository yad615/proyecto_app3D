package com.chavez.patrick.orientatecproyecto

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task

class LoginActivity : AppCompatActivity() {
    private lateinit var registerText: TextView
    private lateinit var googleButton: LinearLayout // Botón de Google (tu diseño usa un LinearLayout)
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Asegúrate de que este archivo XML existe

        // Configura el texto de registro y su listener
        registerText = findViewById(R.id.registerText)
        registerText.setOnClickListener {
            // Inicia RegistroActivity cuando se toca el texto
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        // Configurar Google Sign-In para solicitar la ID del usuario y el perfil básico
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail() // Solicitar el email del usuario
            .build()

        // Crear el cliente de Google Sign-In
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // Agregar listener al botón de Google
        googleButton = findViewById(R.id.google)
        googleButton.setOnClickListener {
            signInWithGoogle()
        }
    }

    // Método para iniciar el flujo de Google Sign-In
    private fun signInWithGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    // Resultado de Google Sign-In
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    // Método para manejar el resultado de la autenticación
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.result
            if (account != null) {
                // Autenticación exitosa, puedes obtener los datos de la cuenta
                val email = account.email
                Log.d("GoogleSignIn", "Email: $email")
                // Aquí puedes redirigir al usuario a la siguiente pantalla
            }
        } catch (e: Exception) {
            Log.w("GoogleSignIn", "Error en Google Sign-In", e)
        }
    }
}
