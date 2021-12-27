package com.example.faturas_app.contract

import com.example.faturas_app.databinding.FragmentSecondBinding

interface ClientContract {

    interface View {
        fun getClientCreditCard() : FragmentSecondBinding
        fun getToken() : String?
    }
}