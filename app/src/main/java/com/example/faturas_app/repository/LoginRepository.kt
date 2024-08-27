package com.example.faturas_app.repository

import androidx.lifecycle.LiveData
import com.example.faturas_app.model.LoginRequest
import com.example.faturas_app.network.api.RetrofitService
import com.example.faturas_app.ui.status.UIStatus

interface LoginRepository  {

    fun getUserToken(loginRequest: LoginRequest): LiveData<UIStatus>
}