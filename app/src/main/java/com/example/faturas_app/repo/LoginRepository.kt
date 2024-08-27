package com.example.faturas_app.repo

import com.example.faturas_app.model.LoginRequest
import com.example.faturas_app.network.api.RetrofitService

class LoginRepository constructor(private val retrofitService: RetrofitService) {

    fun getUserToken(loginRequest: LoginRequest) = retrofitService.gerarToken(loginRequest)
}