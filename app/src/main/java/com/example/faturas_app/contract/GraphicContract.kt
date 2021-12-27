package com.example.faturas_app.contract

import com.github.mikephil.charting.charts.BarChart

interface GraphicContract {

    interface GraficoView {
        fun getBarChart(): BarChart
       fun getToken(): String?
    }



}