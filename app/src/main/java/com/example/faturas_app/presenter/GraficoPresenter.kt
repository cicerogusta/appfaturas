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

data class GraficoPresenter(var view: GraphicContract.GraficoView, val context: Context) :
    SellContract.VendasListPresenter, IDateUtil {
    val list = ArrayList<Venda>()
    val yVals1 = ArrayList<BarEntry>()
    val labels = ArrayList<String>()

     fun getGrafico() {
        view.getBarChart()
    }

     fun setDataGrafico(labels: ArrayList<String>, yVals1: ArrayList<BarEntry>) {
        val barChartService = BarChartService(view.getBarChart(), context)
        barChartService.setData(labels, yVals1)
    }

    override fun getVendas(token: String) {
        val call = RetrofitCall.retrofit().getVendas("Bearer $token")

        call.enqueue(object : Callback<List<Venda>> {
            override fun onResponse(call: Call<List<Venda>>, response: Response<List<Venda>>) {
                if (response.isSuccessful) {
                    response.body()
                        ?.forEach {
                            list.add(it)
                            yVals1.add(BarEntry(list.size.toFloat(), it.valor.toFloat()))
                            labels.add(fornatDataVenda(it.date))
                            setDataGrafico(labels, yVals1)


                        }
                }


            }


            override fun onFailure(call: Call<List<Venda>>, t: Throwable) {
            }

        })

    }

    override fun fornatDataVenda(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        //val output = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val output = SimpleDateFormat("dd/MM")
        val d = sdf.parse(date)
        return output.format(d)
    }
}


