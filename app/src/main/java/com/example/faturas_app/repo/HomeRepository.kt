package com.example.faturas_app.repo

import com.example.faturas_app.network.api.RetrofitService

class HomeRepository constructor(private val retrofitService: RetrofitService) {

    val token =
        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaWNlcm9ndXN0YSIsImlhdCI6MTY0NDE4MDM3MiwiZXhwIjoxNzUyMTgwMzcyfQ.n1XLZBYBfDO8OOpHZnyuwRfVBPt1fK0k49MSbgsme19v1b_9_E9qFKzYuZvzOWOw0TjtId9jh9qg9uiZQvOr-A"

    fun getVendas() = retrofitService.getVendas("Bearer $token")
}