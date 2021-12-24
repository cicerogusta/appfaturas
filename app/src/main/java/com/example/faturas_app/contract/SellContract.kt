package com.example.faturas_app.contract

import com.example.faturas_app.model.apiModel.Venda

interface SellContract {

    interface ListaVendasView {

        fun mostraVendas(vendas: ArrayList<Venda>)




    }

    interface VendasListPresenter {

        interface VendasListView{
            fun setView(listaVendasView: ListaVendasView)
        }

        fun getVendas(token: String)



    }
}