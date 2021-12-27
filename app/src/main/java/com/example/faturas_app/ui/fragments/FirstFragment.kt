package com.example.faturas_app.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.faturas_app.barchart.BarChartService
import com.example.faturas_app.contract.GraphicContract
import com.example.faturas_app.databinding.FragmentFirstBinding
import com.example.faturas_app.presenter.GraficoPresenter
import com.github.mikephil.charting.charts.BarChart

class FirstFragment : Fragment(), GraphicContract.GraficoView {

    lateinit var binding: FragmentFirstBinding
    private lateinit var barChartService: BarChartService
    lateinit var presenter: GraficoPresenter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater)
        barChartService = context?.let { BarChartService(binding.barChart, it) }!!
        presenter = GraficoPresenter(this, requireContext())
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        presenter.getGrafico()
        presenter.getVendas()



    }

    override fun getBarChart(): BarChart {
        return binding.barChart
    }

    override fun getToken(): String? {
        return activity?.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)?.getString("token", null)
    }


}