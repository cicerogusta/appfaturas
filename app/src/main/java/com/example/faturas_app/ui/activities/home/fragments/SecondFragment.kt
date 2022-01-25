package com.example.faturas_app.ui.activities.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.faturas_app.contract.Contract
import com.example.faturas_app.databinding.FragmentSecondBinding
import com.example.faturas_app.presenter.Presenter


class SecondFragment : Fragment(), Contract.View.CreditCardFragmentView {

    lateinit var binding: FragmentSecondBinding
    lateinit var presenter: Presenter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater)
         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            presenter = Presenter(cardFragmentView = this, context = requireContext())
            presenter.getCreditCardValues()




    }

    override fun getClientCreditCard(): FragmentSecondBinding {

        return binding
    }



}

