package com.example.faturas_app.contract

import com.example.faturas_app.databinding.ActivityLoginBinding

interface LoginContract {

    interface View {

        fun getBinding() : ActivityLoginBinding
        fun startNewActivity()
    }

    interface Presenter {
        fun login()


    }
}