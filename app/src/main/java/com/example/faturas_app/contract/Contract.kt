package com.example.faturas_app.contract

import com.example.faturas_app.databinding.ActivityHomeBinding
import com.example.faturas_app.databinding.ActivityLoginBinding
import com.example.faturas_app.databinding.FragmentSecondBinding
import com.example.faturas_app.model.apiModel.Venda
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarEntry
import okhttp3.ResponseBody

interface Contract {

    interface View {
        interface FragmentGraficoView {
            fun getBarChart(): BarChart
        }

        interface HomeView {
            fun mostraVendas(vendas: ArrayList<Venda>)
            fun pageChangeCallBack()
            fun setupTabLayout()
            fun getComponentHomeBinding() : ActivityHomeBinding
            fun finishActivity()
            fun createAlertDialog(titulo: String, mensagem: String,needButton: Boolean )

        }

        interface CreditCardFragmentView {
            fun getClientCreditCard() : FragmentSecondBinding

        }

        interface LoginView {
            fun getComponentLoginBinding() : ActivityLoginBinding
            fun startNewActivity()
            fun setupTextViewLogo()
            fun createAlertDialog(titulo: String, mensagem: String)
            fun verificaCampos(): Boolean

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
        fun refreshtoken(refreshToken: String?)
        fun saveNewAccessToken(accessToken: String)
        fun getNewAccessToken(): String?





        fun getListaVendas()


        fun getRefreshToken(): String?
        fun saveRefreshToken(refreshToken: String?)
        fun verifyIsTokenExpirated(responseBody: ResponseBody?)
    }




}