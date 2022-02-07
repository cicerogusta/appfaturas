package com.example.faturas_app.network.api

import com.example.faturas_app.model.LoginRequest
import com.example.faturas_app.model.Token
import com.example.faturas_app.model.Venda
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitService {
    @POST("/api/auth/signin")
    fun gerarToken(@Body loginRequestRequest: LoginRequest): Call<Token?>

    @GET("/api/vendas")
    fun getVendas(@Header("Authorization") auth: String): Call<List<Venda>>



//    @POST("/api/auth/refreshtoken")
//    fun refreshtoken(@Body refreshToken: String): Call<RefreshToken>

    companion object {

        private val retrofitService: RetrofitService by lazy {
            val retrofit = Retrofit.Builder().baseUrl("http://10.0.0.113:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)
        }

        fun getInstance(): RetrofitService {
            return retrofitService
        }
    }

}