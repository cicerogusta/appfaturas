package com.example.faturas_app.service

import retrofit2.http.POST
import com.example.faturas_app.model.Login
import com.example.faturas_app.model.Usuario
import retrofit2.Call
import retrofit2.http.Body

interface UsuarioService {
    @POST("/auth/autenticar")
    fun login(@Body login: Login?): Call<Usuario?>?
}