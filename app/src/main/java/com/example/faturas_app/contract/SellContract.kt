package com.example.faturas_app.contract

import android.content.SharedPreferences
import com.example.faturas_app.model.apiModel.Venda
import com.github.mikephil.charting.data.BarEntry

interface SellContract {

    interface HomeView {

        fun mostraVendas(vendas: ArrayList<Venda>)
        fun pageChangeCallBack()
        fun setupTabLayout()
        fun getToken(): String?




    }

    interface HomePresenter {


        fun getToken(): String?
        fun getVendas()





    }
}