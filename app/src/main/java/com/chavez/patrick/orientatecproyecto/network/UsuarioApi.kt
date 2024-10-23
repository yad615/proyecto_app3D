package com.chavez.patrick.orientatecproyecto.network


import com.chavez.patrick.orientatecproyecto.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface UsuarioApi {
    @POST("usuarios/registro")
    fun registrarUsuario(@Body usuario: Usuario): Call<Usuario>
}
