package com.example.faturas_app.repository

interface HomeRepository {


    fun getVendas(bearerToken: String)
}