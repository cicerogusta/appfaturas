package com.example.faturas_app.viewmodel.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.faturas_app.barchart.BarChartService
import com.example.faturas_app.model.Venda
import com.example.faturas_app.repo.HomeRepository
import com.example.faturas_app.utli.IDate
import com.github.mikephil.charting.data.BarEntry
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel(private val repository: HomeRepository) : ViewModel(), IDate {

    val liveVenda = MutableLiveData<List<Venda>>()
    val errorMessage = MutableLiveData<String>()
    val listaVendas = ArrayList<Venda>()


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

    fun setDataGrafico(barChartService: BarChartService) {
        val request = repository.getVendas()
        request.enqueue(object : Callback<List<Venda>> {
            override fun onResponse(call: Call<List<Venda>>, response: Response<List<Venda>>) {
                val yVals1 = ArrayList<BarEntry>()
                val labels = ArrayList<String>()
                if (response.isSuccessful) {
                    response.body()?.forEach {
                        listaVendas.add(it)
                        yVals1.add(BarEntry(listaVendas.size.toFloat(), it.valor.toFloat()))
                        labels.add(formataDataVenda(it.date))
                        barChartService.setData(yVals1)

                    }

                }


            }

            override fun onFailure(call: Call<List<Venda>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }

    override fun formataDataVenda(date: String): String {

            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
            val output = SimpleDateFormat("dd/MM", Locale.US)
            val d = sdf.parse(date)
            return output.format(d)
    }


}