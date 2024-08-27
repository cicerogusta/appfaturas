package com.example.faturas_app.viewmodel.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.faturas_app.model.JwtResponse
import com.example.faturas_app.model.LoginRequest
import com.example.faturas_app.repo.LoginRepository
import retrofit2.Call
import retrofit2.Response

class LoginViewModel(
    private val repository: LoginRepository,
) : ViewModel() {

    private val _userToken = MutableLiveData<JwtResponse>()
    val userToken = _userToken

    fun getUserToken(loginRequest: LoginRequest): Call<JwtResponse> {
        return repository.getUserToken(loginRequest)
    }



}