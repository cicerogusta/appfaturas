package com.example.faturas_app.viewmodel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.faturas_app.model.JwtResponse
import com.example.faturas_app.model.LoginRequest
import com.example.faturas_app.repository.LoginRepository
import com.example.faturas_app.repository.LoginRepositoryImp
import com.example.faturas_app.ui.status.UIStatus
import retrofit2.Call

class LoginViewModel(
    private val repository: LoginRepositoryImp,
) : ViewModel() {

    private val _userToken = MutableLiveData<JwtResponse>()
    val userToken = _userToken

    fun getUserToken(loginRequest: LoginRequest): LiveData<UIStatus> {
        return repository.getUserToken(loginRequest)
    }



}