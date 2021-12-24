package com.example.faturas_app.contract

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarEntry

interface GraphicContract {

    interface GraficoView {
        fun getBarChart(): BarChart
    }

}