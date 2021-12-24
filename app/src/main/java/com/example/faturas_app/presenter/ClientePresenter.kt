package com.example.faturas_app.presenter

import com.example.faturas_app.contract.ClientContract
import com.example.faturas_app.contract.SellContract
import com.example.faturas_app.model.apiModel.Cliente
import com.example.faturas_app.model.apiModel.Venda
import com.example.faturas_app.network.retrofit.RetrofitCall
import com.github.mikephil.charting.data.BarEntry
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigDecimal

data class ClientePresenter(val view: ClientContract.View) : SellContract.VendasListPresenter {
    override fun getVendas(token: String) {

        val call = RetrofitCall.retrofit().getVendas("Bearer $token")

        call.enqueue(object : Callback<List<Venda>> {
            override fun onResponse(call: Call<List<Venda>>, response: Response<List<Venda>>) {
                if (response.isSuccessful) {
                    val listaVendas = response.body()
                    if (listaVendas != null) {
                        for (i in listaVendas.indices) {
                            listaVendas.forEach {
                                var disponivel = it.disponivel.toBigDecimal()
                                var gasto = it.valor.toBigDecimal()
                                gasto += it.valor.toBigDecimal() * i.toBigDecimal()
                                disponivel -= it.valor.toBigDecimal()
                                it.disponivel = disponivel.toString()
                                view.getClientCreditCard().disponivel = "R$ $disponivel"
                                view.getClientCreditCard().gasto = "R$ $gasto"
                                view.getClientCreditCard().cardNumber =
                                    lastFourNumber(it.creditCardNumber)
                            }
                        }

                    }
                }


            }


            override fun onFailure(call: Call<List<Venda>>, t: Throwable) {
            }

        })

    }

     fun lastFourNumber(cardNumber: String): String {
        val a = cardNumber.toCharArray()

        return "XXXX XXXX XXXX " + a[12] + a[13] + a[14] + a[15]
    }



}
