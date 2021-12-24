package com.example.faturas_app.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.faturas_app.contract.ClientContract
import com.example.faturas_app.databinding.FragmentSecondBinding
import com.example.faturas_app.model.apiModel.Venda
import com.example.faturas_app.presenter.ClientePresenter
import com.example.faturas_app.presenter.VendaPresenter


class SecondFragment : Fragment(), ClientContract.View {

    lateinit var binding: FragmentSecondBinding
    lateinit var presenter: ClientePresenter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater)
         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token = activity?.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)?.getString("token", null)
        if (token != null) {
            presenter = ClientePresenter(this)
            presenter.getVendas(token)
        }




    }

    override fun getClientCreditCard(): FragmentSecondBinding {

        return binding
    }


}

