package com.example.faturas_app.contract

import com.example.faturas_app.databinding.ActivityLoginBinding

interface LoginContract {

    interface View {

        fun getBinding() : ActivityLoginBinding
        fun startNewActivity()
        fun saveToken(token:String)
    }

    interface Presenter {
        fun login()


    }
}