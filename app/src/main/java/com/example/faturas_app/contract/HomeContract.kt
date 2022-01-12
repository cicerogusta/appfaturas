package com.example.faturas_app.contract

import android.content.SharedPreferences
import com.example.faturas_app.databinding.ActivityHomeBinding
import com.example.faturas_app.databinding.ActivityLoginBinding
import com.example.faturas_app.databinding.FragmentSecondBinding
import com.example.faturas_app.model.apiModel.Venda
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarEntry

interface HomeContract {

    interface View {
        interface FragmentGraficoView {
            fun getBarChart(): BarChart
        }

        interface HomeView {
            fun mostraVendas(vendas: ArrayList<Venda>)
            fun pageChangeCallBack()
            fun setupTabLayout()
            fun getComponentHomeBinding() : ActivityHomeBinding

        }

        interface CreditCardFragmentView {
            fun getClientCreditCard() : FragmentSecondBinding

        }

        interface LoginView {
            fun getComponentLoginBinding() : ActivityLoginBinding
            fun startNewActivity()

        }
    }


    interface Presenter {

        fun getGraficoVendas()
        fun configGrafico()
        fun setDataGrafico(labels: ArrayList<String>, yVals1: ArrayList<BarEntry>)
        fun formatDataVenda(date: String): String
        fun getCreditCardValues()

        fun lastFourNumber(cardNumber: String): String
        fun login()




        fun getListaVendas()


    }




}