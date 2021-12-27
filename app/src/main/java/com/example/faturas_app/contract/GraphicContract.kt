package com.example.faturas_app.contract

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarEntry

interface GraphicContract {

    interface GraficoView {
        fun getBarChart(): BarChart
       fun getToken(): String?
    }

    interface GraficoPresenter {
        fun getToken(): String?
        fun getVendas()
        fun getGrafico()
        fun setDataGrafico(labels: ArrayList<String>, yVals1: ArrayList<BarEntry>)
        fun formatDataVenda(date: String): String
    }



}