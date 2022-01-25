package com.example.faturas_app.util

import com.example.faturas_app.network.api.ApiService

interface TokenUtil {

    fun saveIsTokenExpirated()
    fun getIsTokenExpirated(): Boolean?
    fun refreshtoken(refreshToken: String?)
    fun saveRefreshToken(refreshToken: String)
    fun getRefreshToken(): String?
    fun saveNewAccessToken(accessToken: String)
    fun getNewAccessToken(): String?
    fun generateCallWithNewAccessToken(call: ApiService, refreshToken: String?)
    fun saveToken(token: String)
    fun getToken(): String?
}