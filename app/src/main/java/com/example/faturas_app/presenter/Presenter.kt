package com.example.faturas_app.presenter

import android.content.Context
import android.content.SharedPreferences
import com.example.faturas_app.barchart.BarChartService
import com.example.faturas_app.contract.HomeContract
import com.example.faturas_app.model.apiModel.Login
import com.example.faturas_app.model.apiModel.Token
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
import kotlin.math.log

class Presenter(
    val graficoView: HomeContract.View.FragmentGraficoView? = null,
    context: Context? = null,
    val homeView: HomeContract.View.HomeView? = null,
    val cardFragmentView: HomeContract.View.CreditCardFragmentView? = null,
    val loginView: HomeContract.View.LoginView? = null,
) :
    HomeContract.Presenter, IDateUtil {


    val listaVendas = ArrayList<Venda>()
    val yVals1 = ArrayList<BarEntry>()
    val labels = ArrayList<String>()
    private val barChartService =
        context?.let { graficoView?.let { it1 -> BarChartService(it1.getBarChart(), it) } }


    override fun configGrafico() {
        barChartService?.initBarChart()
        barChartService?.leftAxisSetup()
        barChartService?.rightAxisSetup()
        barChartService?.legendSetup()
    }

    override fun setDataGrafico(labels: ArrayList<String>, yVals1: ArrayList<BarEntry>) {
        barChartService?.setData(labels, yVals1)
    }


    override fun formatDataVenda(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val output = SimpleDateFormat("dd/MM", Locale.US)
        val d = sdf.parse(date)
        return output.format(d)
    }

    override fun getGraficoVendas() {


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

    override fun getListaVendas() {

        val swipeRefreshLayout = homeView?.getComponentHomeBinding()?.swiperefresh


        val listaVendas = ArrayList<Venda>()


        val call = RetrofitCall.retrofit().getVendas("Bearer ${getToken()}")

        call.enqueue(object : Callback<List<Venda>> {
            override fun onResponse(call: Call<List<Venda>>, response: Response<List<Venda>>) {
                swipeRefreshLayout?.isRefreshing = false
                if (response.isSuccessful) {
                    response.body()
                        ?.forEach {
                            it.date = formatDataVenda(it.date)
                            it.valor = "R$ " + it.valor
                            listaVendas.add(it)
                            homeView?.mostraVendas(listaVendas)


                        }
                }


            }


            override fun onFailure(call: Call<List<Venda>>, t: Throwable) {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.isRefreshing = false
                }

            }

        })

    }

    override fun getCreditCardValues() {
        val call = RetrofitCall.retrofit().getVendas("Bearer ${getToken()}")

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
                                cardFragmentView?.getClientCreditCard()?.disponivel =
                                    "R$ $disponivel"
                                cardFragmentView?.getClientCreditCard()?.gasto = "R$ $gasto"
                                cardFragmentView?.getClientCreditCard()?.cardNumber =
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

    override fun saveToken(token: String) {
        getSharedPreferences()?.edit()?.putString("token", token)?.apply()

    }

    override fun getToken(): String? {
        return getSharedPreferences()?.getString("token", null)
    }

    override fun getSharedPreferences(): SharedPreferences? {
        return loginView?.getPreferences()
    }

    override fun lastFourNumber(cardNumber: String): String {
        val a = cardNumber.toCharArray()

        return "XXXX XXXX XXXX " + a[12] + a[13] + a[14] + a[15]
    }

    override fun login() {
        val login = Login(
            loginView?.getComponentLoginBinding()?.editEmail?.text.toString(),
            loginView?.getComponentLoginBinding()?.editSenha?.text.toString()
        )
        val call = RetrofitCall.retrofit().gerarToken(login)

        call.enqueue(object : Callback<Token?> {
            override fun onResponse(call: Call<Token?>, response: Response<Token?>) {

                if (response.isSuccessful) {
                    loginView?.startNewActivity()
                    response.body()?.token?.let { saveToken(it) }
//                    response.body()?.refreshToken?.let { view.saveRefreshToken(it) }

                }
            }

            override fun onFailure(call: Call<Token?>, t: Throwable) {


            }

        })

    }
}