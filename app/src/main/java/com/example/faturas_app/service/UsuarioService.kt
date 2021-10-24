package com.example.faturas_app.service

import com.example.faturas_app.model.Login
import com.example.faturas_app.model.Usuario
import com.example.faturas_app.model.Venda
import retrofit2.Call
import retrofit2.http.*

interface UsuarioService {
    @POST("/auth")
    fun gerarToken(@Body login: Login): Call<Usuario?>?

    @GET("/api/cliente/{id}")
     fun getClienteById(@Header("Authorization") auth: String, @Path(value = "id") id: Long): Call<Usuario>

    @POST("/api/cliente")
    fun setCliente(@Header("Authorization") auth: String, @Body usuario: Usuario): Call<Usuario>
    @GET()
    fun getVendaById(@Header("Authorization") auth: String, @Path(value = "id") id: Long): Call<Venda>
}