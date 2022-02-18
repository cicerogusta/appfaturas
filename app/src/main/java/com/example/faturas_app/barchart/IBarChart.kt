package com.example.faturas_app.barchart

import com.github.mikephil.charting.data.BarEntry

interface IBarChart {

    fun configTouchBarChart()
    fun leftAxisSetup()
    fun rightAxisSetup()
    fun xAxisSetup()
    fun legendSetup()
    fun setData(yVals1: ArrayList<BarEntry>)
    fun destaque()



}