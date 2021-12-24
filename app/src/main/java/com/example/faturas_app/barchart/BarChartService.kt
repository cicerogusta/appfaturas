package com.example.faturas_app.barchart

import android.content.Context
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet

data class BarChartService(private val barChart: BarChart, val context: Context) : IBarChart {
    override fun initBarChart() {
        barChart.setNoDataText("Carregando...")
        barChart.setDrawBarShadow(false)
        barChart.setDrawValueAboveBar(true)
        barChart.description.isEnabled = false
        barChart.setMaxVisibleValueCount(22)
        barChart.setPinchZoom(false)
        barChart.setDrawGridBackground(false)
        barChart.isDoubleTapToZoomEnabled = false
    }

    override fun leftAxisSetup() {
        barChart.axisLeft
        barChart.axisLeft.valueFormatter = MyAxisValueFormatter()
        barChart.axisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        barChart.axisLeft.spaceTop = 15f
        barChart.axisLeft.axisMinimum = 0f
    }

    override fun rightAxisSetup() {
        barChart.axisRight
        barChart.axisRight.setDrawGridLines(false)
        barChart.axisRight.isEnabled = false
    }

    override fun legendSetup() {
        barChart.legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        barChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        barChart.legend.orientation = Legend.LegendOrientation.HORIZONTAL
        barChart.legend.setDrawInside(false)
        barChart.legend.form = Legend.LegendForm.SQUARE
        barChart.legend.formSize = 9f
        barChart.legend.textSize = 11f
        barChart.legend.xEntrySpace = 4f
    }

    override fun setData(labels: ArrayList<String>, yVals1: ArrayList<BarEntry>) {
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.granularity = 1f
        barChart.xAxis.labelCount = 7
        barChart.xAxis.valueFormatter = LabelFormatter(labels)


        val mv = XYMarkerView(context)
        mv.chartView = barChart
        barChart.marker = mv


        val set1: BarDataSet

        if (barChart.data != null && barChart.data.dataSetCount > 0) {

            set1 = barChart.data.getDataSetByIndex(0) as BarDataSet
            set1.values = yVals1
            barChart.data.notifyDataChanged()
            barChart.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(yVals1, "Compras")

            set1.setDrawIcons(false)

            val colors = ArrayList<Int>()

            context.let { ContextCompat.getColor(it, android.R.color.holo_orange_light) }.let {
                colors.add(
                    it
                )
            }
            context.let { ContextCompat.getColor(it, android.R.color.holo_blue_light) }.let {
                colors.add(
                    it
                )
            }
            context.let { ContextCompat.getColor(it, android.R.color.holo_orange_light) }.let {
                colors.add(
                    it
                )
            }
            context.let { ContextCompat.getColor(it, android.R.color.holo_green_light) }.let {
                colors.add(
                    it
                )
            }
            context.let { ContextCompat.getColor(it, android.R.color.holo_red_light) }.let {
                colors.add(
                    it
                )
            }
            context.let { ContextCompat.getColor(it, android.R.color.holo_orange_dark) }.let {
                colors.add(
                    it
                )
            }
            context.let { ContextCompat.getColor(it, android.R.color.holo_blue_dark) }.let {
                colors.add(
                    it
                )
            }
            context.let { ContextCompat.getColor(it, android.R.color.holo_orange_dark) }.let {
                colors.add(
                    it
                )
            }
            context.let { ContextCompat.getColor(it, android.R.color.holo_green_dark) }.let {
                colors.add(
                    it
                )
            }
            context.let { ContextCompat.getColor(it, android.R.color.holo_red_dark) }.let {
                colors.add(
                    it
                )
            }
            context.let { ContextCompat.getColor(it, android.R.color.holo_purple) }.let {
                colors.add(
                    it
                )
            }
            context.let { ContextCompat.getColor(it, android.R.color.holo_blue_bright) }.let {
                colors.add(
                    it
                )
            }

            set1.colors = colors


            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            val data = BarData(dataSets)
            data.setValueTextSize(10f)
            data.barWidth = 0.9f

            barChart.data = data

        }

    }

}

