package com.example.faturas_app.ui.activities.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.faturas_app.barchart.BarChartService
import com.example.faturas_app.databinding.FragmentFirstBinding
import com.example.faturas_app.network.api.RetrofitService
import com.example.faturas_app.repo.HomeRepository
import com.example.faturas_app.viewmodel.home.HomeViewModel
import com.example.faturas_app.viewmodel.home.HomeViewModelFactory

class GraphicFragment : Fragment() {

    private val binding by lazy {
        FragmentFirstBinding.inflate(layoutInflater)
    }
     private val barChartService by lazy {
         BarChartService(binding.barChart, requireContext())

     }
    private val retrofitService by lazy {
        RetrofitService.getInstance()
    }
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            HomeViewModelFactory(HomeRepository((retrofitService)))
        )[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        barChartService.configTouchBarChart()
        barChartService.rightAxisSetup()
        barChartService.leftAxisSetup()
        barChartService.legendSetup()
        viewModel.setDataGrafico(barChartService)


    }

    override fun onResume() {
        super.onResume()
        viewModel.setDataGrafico(barChartService)

    }


}