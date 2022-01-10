package com.example.faturas_app.ui.activities.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.faturas_app.barchart.BarChartService
import com.example.faturas_app.contract.HomeContract
import com.example.faturas_app.databinding.FragmentFirstBinding
import com.example.faturas_app.presenter.Presenter
import com.github.mikephil.charting.charts.BarChart

class FirstFragment : Fragment(), HomeContract.View.FragmentGraficoView {

    lateinit var binding: FragmentFirstBinding
    private lateinit var barChartService: BarChartService
    lateinit var presenter: Presenter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater)
        barChartService = context?.let { BarChartService(binding.barChart, it) }!!
        presenter = Presenter(this)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        presenter.configGrafico()
        presenter.getGraficoVendas()



    }

    override fun getBarChart(): BarChart {
        return binding.barChart
    }



}