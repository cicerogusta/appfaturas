package com.example.faturas_app.network.api

import com.example.faturas_app.model.apiModel.Cliente
import com.example.faturas_app.model.apiModel.Login
import com.example.faturas_app.model.apiModel.Token
import com.example.faturas_app.model.apiModel.Venda
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("/auth")
    fun gerarToken(@Body login: Login): Call<Token?>

    @GET("/api/vendas")
    fun getVendas(@Header("Authorization") auth: String): Call<List<Venda>>

    @GET("/api/cliente/{email}")
    fun getCliente(@Header("Authorization") auth: String, @Path(value = "email") email: String): Call<Cliente>
}