package com.example.faturas_app.barchart

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import java.time.YearMonth
import java.util.*
import kotlin.collections.ArrayList

data class BarChartService(private val barChart: BarChart, val context: Context) : IBarChart {
    override fun configTouchBarChart() {
        barChart.setTouchEnabled(false)
        barChart.isDragEnabled = false
        barChart.setScaleEnabled(true)
        barChart.isScaleXEnabled = true
        barChart.setPinchZoom(false)
        barChart.isDoubleTapToZoomEnabled = false
        barChart.isHighlightPerTapEnabled = false

    }

    override fun leftAxisSetup() {

    }

    override fun rightAxisSetup() {

    }

    override fun xAxisSetup() {
        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textSize = 12f
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
    }

    override fun legendSetup() {

    }

    override fun setData(yVals1: ArrayList<BarEntry>) {
    }

    override fun destaque() {
        barChart.isHighlightPerDragEnabled = true

    }

}

