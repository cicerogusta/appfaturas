package com.example.faturas_app.network.api

import com.example.faturas_app.model.apiModel.LoginRequest
import com.example.faturas_app.model.apiModel.RefreshToken
import com.example.faturas_app.model.apiModel.Token
import com.example.faturas_app.model.apiModel.Venda
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/api/auth/signin")
    fun gerarToken(@Body loginRequestRequest: LoginRequest): Call<Token?>

    @GET("/api/vendas")
    fun getVendas(@Header("Authorization") auth: String): Call<List<Venda>>

    @POST("/api/auth/isTokenExpirated")
    fun isTokenExpirated(@Body token: String): Call<Boolean>

    @POST("/api/auth/refreshtoken")
    fun refreshtoken(@Body refreshToken: String): Call<RefreshToken>

    @POST("/api/test")
    fun returnApiConnection(): Call<String>

}