package com.example.faturas_app.presenter

import com.example.faturas_app.contract.LoginContract
import com.example.faturas_app.model.apiModel.Login
import com.example.faturas_app.model.apiModel.Token
import com.example.faturas_app.network.retrofit.RetrofitCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {


    override fun login() {
        val login = Login(
            view.getBinding().editEmail.text.toString(),
            view.getBinding().editSenha.text.toString()
        )
        val call = RetrofitCall.retrofit().gerarToken(login)

        call.enqueue(object : Callback<Token?> {
            override fun onResponse(call: Call<Token?>, response: Response<Token?>) {

                if (response.isSuccessful) {
                    view.startNewActivity()
                    response.body()?.token?.let { view.saveToken(it) }

                }
            }

            override fun onFailure(call: Call<Token?>, t: Throwable) {


            }

        })

    }


}



