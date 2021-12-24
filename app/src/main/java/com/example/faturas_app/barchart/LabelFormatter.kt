package com.example.faturas_app.barchart

import com.github.mikephil.charting.formatter.ValueFormatter
import java.util.ArrayList

class LabelFormatter(private val mLabels: ArrayList<String>) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return mLabels[value.toInt() - 1]
    }
}