package com.example.faturas_app.network.api

import com.example.faturas_app.model.apiModel.RefreshToken
import com.example.faturas_app.model.apiModel.Login
import com.example.faturas_app.model.apiModel.Token
import com.example.faturas_app.model.apiModel.Venda
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/auth")
    fun gerarToken(@Body login: Login): Call<Token?>

    @GET("/api/vendas")
    fun getVendas(@Header("Authorization") auth: String): Call<List<Venda>>

    @POST("/api/auth/isExpiratedToken")
    fun isExpiratedToken(@Body token: String): Call<Boolean>
}