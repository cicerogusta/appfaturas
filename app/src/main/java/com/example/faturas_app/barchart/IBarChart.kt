package com.example.faturas_app.barchart

import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarEntry

interface IBarChart {

    fun initBarChart()
    fun leftAxisSetup()
    fun rightAxisSetup()
    fun legendSetup()
    fun setData(labels: ArrayList<String>, yVals1: ArrayList<BarEntry>)


}