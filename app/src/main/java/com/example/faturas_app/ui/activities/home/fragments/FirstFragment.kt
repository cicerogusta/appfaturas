package com.example.faturas_app.ui.activities.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.faturas_app.barchart.BarChartService
import com.example.faturas_app.barchart.LabelFormatter
import com.example.faturas_app.barchart.XYMarkerView
import com.example.faturas_app.databinding.FragmentFirstBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import java.lang.Exception

class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstBinding
    lateinit var barChartService: BarChartService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(layoutInflater)
        barChartService = BarChartService(binding.barChart, requireContext())
//        Toast.makeText(requireContext(), "FRAGMENTO CRIADO!", Toast.LENGTH_LONG).show()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        verifyIsNull(barChartService)
//        try {
//            barChartService.initBarChart()
//            barChartService.leftAxisSetup()
//            barChartService.rightAxisSetup()
//            barChartService.legendSetup()
//
//        }catch (e: Exception) {
//            e.printStackTrace()
//        }

    }

    override fun onStart() {
        super.onStart()

        try {
            barChartService = BarChartService(binding.barChart, requireContext())

            barChartService.initBarChart()
            barChartService.leftAxisSetup()
            barChartService.rightAxisSetup()
            barChartService.legendSetup()

        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun verifyIsNull(barChartService: BarChartService) {
        if (barChartService.equals(null)) {
            Toast.makeText(requireContext(), "BarChartService Vazio!", Toast.LENGTH_LONG).show()

        }
    }

     fun setData(labels: ArrayList<String>, yVals1: ArrayList<BarEntry>) {
        try {
            barChartService.setData(labels, yVals1)
        }catch (e:Exception) {
            e.printStackTrace()
        }
    }
}