package com.example.faturas_app.network.retrofit

import com.example.faturas_app.network.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCall {

    fun retrofit(): ApiService {

        val builder = Retrofit.Builder().baseUrl("http://10.0.0.113:8080/")
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder.build()

        return retrofit.create(ApiService::class.java)
    }
}