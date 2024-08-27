package com.example.faturas_app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.faturas_app.model.JwtResponse
import com.example.faturas_app.model.LoginRequest
import com.example.faturas_app.model.Venda
import com.example.faturas_app.network.api.RetrofitService
import com.example.faturas_app.ui.status.Status
import com.example.faturas_app.ui.status.UIStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepositoryImp(
    private val apiService: RetrofitService
) : HomeRepository {


    private val _vendas = MutableLiveData<List<Venda>>()
    val vendas: LiveData<List<Venda>> get() = _vendas
    override fun getVendas(bearerToken: String) {
        apiService.getVendas(bearerToken).enqueue(object : Callback<List<Venda>> {
            override fun onResponse(call: Call<List<Venda>>, response: Response<List<Venda>>) {
                if (response.isSuccessful) {
                }
            }

            override fun onFailure(call: Call<List<Venda>>, t: Throwable) {
            }

        })
    }


}
