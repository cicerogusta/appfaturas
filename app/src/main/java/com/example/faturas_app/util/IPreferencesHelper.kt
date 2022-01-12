package com.example.faturas_app.util

interface IPreferencesHelper {

    fun saveToken(token: String)
    fun getToken(): String?
}