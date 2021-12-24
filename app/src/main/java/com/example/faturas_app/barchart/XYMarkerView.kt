package com.example.faturas_app.barchart

import android.content.Context
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.components.MarkerView
import com.example.faturas_app.R
import android.widget.TextView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import java.text.DecimalFormat

class XYMarkerView(context: Context?) :
    MarkerView(context, R.layout.custom_marker_view) {
    private val tvContent: TextView = findViewById(R.id.tvContent)
    private val format: DecimalFormat = DecimalFormat("#,##0.00")

    override fun refreshContent(e: Entry, highlight: Highlight) {

        tvContent.text = format.format(e.y.toDouble()) + "R$"
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }

}