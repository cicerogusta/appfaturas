package com.example.faturas_app.viewmodel.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.faturas_app.model.Venda
import com.example.faturas_app.repo.HomeRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    val liveVenda = MutableLiveData<List<Venda>>()
    val errorMessage = MutableLiveData<String>()

    fun getVendas() {

        val request = repository.getVendas()
        request.enqueue(object : Callback<List<Venda>> {
            override fun onResponse(call: Call<List<Venda>>, response: Response<List<Venda>>) {
                liveVenda.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Venda>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }


}