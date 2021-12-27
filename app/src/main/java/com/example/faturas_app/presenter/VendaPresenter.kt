package com.example.faturas_app.presenter

import com.example.faturas_app.contract.SellContract
import com.example.faturas_app.model.apiModel.Venda
import com.example.faturas_app.network.retrofit.RetrofitCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class VendaPresenter(private var view: SellContract.HomeView) :
    SellContract.HomePresenter {
    override fun getToken(): String? {
       return view.getToken()
    }

    override fun getVendas() {


        val list = ArrayList<Venda>()


        val call = RetrofitCall.retrofit().getVendas("Bearer ${getToken()}")

        call.enqueue(object : Callback<List<Venda>> {
            override fun onResponse(call: Call<List<Venda>>, response: Response<List<Venda>>) {
                if (response.isSuccessful) {
                    response.body()
                        ?.forEach {
                            it.date = fornatDataVenda(it.date)
                            it.valor = "R$ " + it.valor
                            list.add(it)
                            view.mostraVendas(list)


                        }
                }


            }


            override fun onFailure(call: Call<List<Venda>>, t: Throwable) {
            }

        })

    }

    fun fornatDataVenda(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val output = SimpleDateFormat("dd/MM", Locale.US)
        val d = sdf.parse(date)



        return output.format(d)
    }

}




