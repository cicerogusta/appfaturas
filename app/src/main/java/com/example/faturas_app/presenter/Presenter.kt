package com.example.faturas_app.presenter

import android.content.Context
import android.content.SharedPreferences
import com.example.faturas_app.barchart.BarChartService
import com.example.faturas_app.contract.Contract
import com.example.faturas_app.model.apiModel.LoginRequest
import com.example.faturas_app.model.apiModel.RefreshToken
import com.example.faturas_app.model.apiModel.Token
import com.example.faturas_app.model.apiModel.Venda
import com.example.faturas_app.network.retrofit.RetrofitCall
import com.example.faturas_app.util.IDateUtil
import com.example.faturas_app.util.IPreferencesHelper
import com.github.mikephil.charting.data.BarEntry
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class Presenter(
    val graficoView: Contract.View.FragmentGraficoView? = null,
    val context: Context,
    val view: Contract.View.HomeView? = null,
    val cardFragmentView: Contract.View.CreditCardFragmentView? = null,
    val loginView: Contract.View.LoginView? = null,
) :
    Contract.Presenter, IDateUtil, IPreferencesHelper {


    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
    private var token = getToken()
    private var refreshToken = getRefreshToken()
    val listaVendas = ArrayList<Venda>()
    private val barChartService =
        context.let { graficoView?.let { it1 -> BarChartService(it1.getBarChart(), it) } }


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


        val call = RetrofitCall.retrofit().getVendas("Bearer $token")

        call.enqueue(object : Callback<List<Venda>> {
            override fun onResponse(call: Call<List<Venda>>, response: Response<List<Venda>>) {
                val yValues = ArrayList<BarEntry>()
                val labels = ArrayList<String>()
                if (response.isSuccessful) {
                    response.body()
                        ?.forEach {
                            listaVendas.add(it)
                            yValues.add(BarEntry(listaVendas.size.toFloat(), it.valor.toFloat()))
                            labels.add(formatDataVenda(it.date))
                            setDataGrafico(labels, yValues)


                        }
                }


            }


            override fun onFailure(call: Call<List<Venda>>, t: Throwable) {
            }

        })

    }

    override fun getListaVendas() {


        val call = RetrofitCall.retrofit()

        call.getVendas("Bearer $token").enqueue(object : Callback<List<Venda>> {
            override fun onResponse(call: Call<List<Venda>>, response: Response<List<Venda>>) {

                try {
                    verifyIsTokenExpirated(response.errorBody())
                    val swipeRefreshLayout = view?.getComponentHomeBinding()?.swiperefresh

                    swipeRefreshLayout?.isRefreshing = false
                    response.body()
                        ?.forEach {
                            it.date = formatDataVenda(it.date)
                            it.valor = "R$ " + it.valor
                            listaVendas.add(it)
                            view?.mostraVendas(listaVendas)


                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }


            }


            override fun onFailure(call: Call<List<Venda>>, t: Throwable) {
//                if (swipeRefreshLayout != null) {
//                    swipeRefreshLayout.isRefreshing = false
//                }

            }

        })


    }

    override fun getCreditCardValues() {
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

    override fun lastFourNumber(cardNumber: String): String {
        val a = cardNumber.toCharArray()

        return "XXXX XXXX XXXX " + a[12] + a[13] + a[14] + a[15]
    }

    override fun login() {
        val login = LoginRequest(
            loginView?.getComponentLoginBinding()?.editEmail?.text.toString(),
            loginView?.getComponentLoginBinding()?.editSenha?.text.toString()
        )
        val call = RetrofitCall.retrofit().gerarToken(login)

        call.enqueue(object : Callback<Token?> {
            override fun onResponse(call: Call<Token?>, response: Response<Token?>) {

                try {
                    loginView?.startNewActivity()
                    response.body()?.accessToken?.let { saveToken(it) }
                    response.body()?.let { saveRefreshToken(it.refreshToken) }

                } catch (e: Exception) {
//                    refreshtoken(response.body()?.refreshToken)
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<Token?>, t: Throwable) {


            }

        })

    }

    override fun refreshtoken(refreshToken: String?) {

        refreshToken?.let { RetrofitCall.retrofit().refreshtoken(it) }
            ?.enqueue(object : Callback<RefreshToken> {
                override fun onResponse(
                    call: Call<RefreshToken>,
                    response: Response<RefreshToken>
                ) {
                    try {
                        if (response.errorBody()?.string()?.contains("403") == true) {
                            view?.createAlertDialog(
                                "Sessão expirada!",
                                "Sua sessão expirou, faça o login novamente!",
                                true
                            )
                        }
                        response.body()
                            ?.let { it.accessToken?.let { it1 -> saveNewAccessToken(it1) } }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call<RefreshToken>, t: Throwable) {

                }

            })
    }


    override fun saveNewAccessToken(accessToken: String) {
        sharedPreferences.edit()?.putString("newAccessToken", accessToken)?.apply()
    }

    override fun getNewAccessToken(): String? {
        return sharedPreferences.getString("newAccessToken", null)
    }


    override fun saveToken(token: String) {
        sharedPreferences.edit()?.putString("token", token)?.apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    override fun getRefreshToken(): String? = sharedPreferences.getString("refreshToken", null)
    override fun saveRefreshToken(refreshToken: String?) {
        sharedPreferences.edit().putString("refreshToken", refreshToken).apply()

    }

    override fun verifyIsTokenExpirated(responseBody: ResponseBody?) {
        if (responseBody != null) {
            if (responseBody.string().contains("401")) {
                refreshtoken(refreshToken)

            }


        }
    }
}