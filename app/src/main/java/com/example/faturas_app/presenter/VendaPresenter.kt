package com.example.faturas_app.presenter

import com.example.faturas_app.contract.SellContract
import com.example.faturas_app.model.apiModel.Venda
import com.example.faturas_app.network.retrofit.RetrofitCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

data class VendaPresenter(private var view: SellContract.ListaVendasView) :
    SellContract.VendasListPresenter.VendasListView, SellContract.VendasListPresenter {

     override fun setView(listaVendasView: SellContract.ListaVendasView) {
        view = listaVendasView
    }

    override fun getVendas(token: String) {

        val list = ArrayList<Venda>()


        val call = RetrofitCall.retrofit().getVendas("Bearer $token")

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
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        //val output = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val output = SimpleDateFormat("dd/MM")
        val d = sdf.parse(date)



        return output.format(d)
    }

}




