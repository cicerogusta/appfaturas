package com.example.faturas_app.presenter

import android.content.Context
import com.example.faturas_app.barchart.BarChartService
import com.example.faturas_app.contract.GraphicContract
import com.example.faturas_app.contract.SellContract
import com.example.faturas_app.model.apiModel.Venda
import com.example.faturas_app.network.retrofit.RetrofitCall
import com.example.faturas_app.util.IDateUtil
import com.github.mikephil.charting.data.BarEntry
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class GraficoPresenter(var view: GraphicContract.GraficoView, val context: Context) :
    GraphicContract.GraficoPresenter, IDateUtil {
    val listaVendas = ArrayList<Venda>()
    val yVals1 = ArrayList<BarEntry>()
    val labels = ArrayList<String>()
    private val barChartService = BarChartService(view.getBarChart(), context)


   override fun getGrafico() {
        barChartService.initBarChart()
        barChartService.leftAxisSetup()
        barChartService.rightAxisSetup()
        barChartService.legendSetup()
    }

     override fun setDataGrafico(labels: ArrayList<String>, yVals1: ArrayList<BarEntry>) {
        barChartService.setData(labels, yVals1)
    }

    override fun getToken(): String? {
        return view.getToken()
    }


    override fun getVendas() {


        val call = RetrofitCall.retrofit().getVendas("Bearer ${getToken()}")

        call.enqueue(object : Callback<List<Venda>> {
            override fun onResponse(call: Call<List<Venda>>, response: Response<List<Venda>>) {
                if (response.isSuccessful) {
                    response.body()
                        ?.forEach {
                            listaVendas.add(it)
                            yVals1.add(BarEntry(listaVendas.size.toFloat(), it.valor.toFloat()))
                            labels.add(formatDataVenda(it.date))
                            setDataGrafico(labels, yVals1)


                        }
                }


            }


            override fun onFailure(call: Call<List<Venda>>, t: Throwable) {
            }

        })

    }

    override fun formatDataVenda(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val output = SimpleDateFormat("dd/MM", Locale.US)
        val d = sdf.parse(date)
        return output.format(d)
    }
}


