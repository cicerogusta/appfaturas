package com.example.faturas_app.repo

import com.example.faturas_app.network.api.RetrofitService

class HomeRepository constructor(private val retrofitService: RetrofitService) {

    val token =
        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaWNlcm9ndXN0YSIsImlhdCI6MTY0NDI0ODAwNywiZXhwIjoxNzUyMjQ4MDA3fQ.Eap_tOzR2ugu9xAt47gXxVwj9_wSHLP68Q9xYeqtr6u0ThjnQZgA3KZiI3YM7UT72qSLyBcTRl06tYTmy99X8Q"

    fun getVendas() = retrofitService.getVendas("Bearer $token")
}