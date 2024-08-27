package com.example.faturas_app.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.faturas_app.model.JwtResponse
import com.example.faturas_app.model.LoginRequest
import com.example.faturas_app.network.api.RetrofitService
import com.example.faturas_app.ui.status.Status
import com.example.faturas_app.ui.status.UIStatus
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepositoryImp(
    private val apiService: RetrofitService
) : LoginRepository {

    override fun getUserToken(loginRequest: LoginRequest): LiveData<UIStatus> {
        val uiStatus = MutableLiveData<UIStatus>()
        uiStatus.value = UIStatus(Status.LOADING, "Carregando...")

        apiService.gerarToken(loginRequest).enqueue(object : Callback<JwtResponse> {
            override fun onResponse(call: Call<JwtResponse>, response: Response<JwtResponse>) {
                if (response.isSuccessful) {
                    uiStatus.value = UIStatus(Status.SUCCESS, "Usuário validado")
                } else {
                    try {
                        val errorBody = response.errorBody()?.string()
                        val jsonObject = JSONObject(errorBody)
                        val errorMessage = jsonObject.getString("message")
                        uiStatus.value = UIStatus(Status.ERROR, errorMessage)
                        Log.e("AuthError", errorMessage)
                    } catch (e: Exception) {
                        uiStatus.value = UIStatus(Status.ERROR, "Erro desconhecido")
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<JwtResponse>, t: Throwable) {
                uiStatus.value = UIStatus(Status.ERROR, t.message ?: "Erro de rede")
            }
        })

        return uiStatus
    }
}
