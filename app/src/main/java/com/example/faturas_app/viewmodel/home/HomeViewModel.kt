package com.example.faturas_app.viewmodel.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.faturas_app.barchart.BarChartService
import com.example.faturas_app.model.Venda
import com.example.faturas_app.repository.HomeRepository
import com.example.faturas_app.repository.HomeRepositoryImp
import com.example.faturas_app.utli.IDate
import com.github.mikephil.charting.data.BarEntry
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel(
    private val repository: HomeRepositoryImp,
) : ViewModel(), IDate {




    fun getVendas() {

    }


//    fun setDataGrafico(barChartService: BarChartService) {
//        val yVals1 = ArrayList<BarEntry>()
//        val labels = ArrayList<String>()
//        liveVenda.value?.forEach {
//            listaVendas.add(it)
//            yVals1.add(BarEntry(listaVendas.size.toFloat(), it.valor.toFloat()))
//            labels.add(formataDataVenda(it.date))
//            barChartService.setData(yVals1)
//        }
//
//
//    }

    override fun formataDataVenda(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val output = SimpleDateFormat("dd/MM", Locale.US)
        val d = sdf.parse(date)
        return output.format(d)
    }
}