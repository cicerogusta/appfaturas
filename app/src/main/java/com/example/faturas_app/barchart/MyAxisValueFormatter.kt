package com.example.faturas_app.barchart

import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat

class MyAxisValueFormatter : ValueFormatter() {
    private val mFormat = DecimalFormat("#,##0.00")

    override fun getFormattedValue(value: Float): String {

        return "R$" + mFormat.format(value.toDouble())
    }

}