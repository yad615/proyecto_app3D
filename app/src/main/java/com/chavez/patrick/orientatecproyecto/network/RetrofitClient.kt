package com.chavez.patrick.orientatecproyecto.network

import com.chavez.patrick.orientatecproyecto.model.Usuario
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: UsuarioApi = retrofit.create(UsuarioApi::class.java)
}
