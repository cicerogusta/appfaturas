package com.example.faturas_app.ui.activitys

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.faturas_app.R
import com.example.faturas_app.adapter.AdapterMovimentacao
import com.example.faturas_app.databinding.ActivityHomeBinding
import com.example.faturas_app.model.apiModel.Venda
import com.example.faturas_app.ui.fragments.FirstFragment
import com.example.faturas_app.ui.fragments.SecondFragment
import com.example.faturas_app.contract.SellContract
import com.example.faturas_app.presenter.VendaPresenter
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat

class HomeActivity : AppCompatActivity(), SellContract.ListaVendasView {

    lateinit var binding: ActivityHomeBinding
    lateinit var adapterVenda: AdapterMovimentacao
    val graphicFragment = FirstFragment()
    val cardFragment = SecondFragment()

    lateinit var presenter: VendaPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pager.adapter = SlidePagerAdapter(supportFragmentManager, lifecycle)
        setupTabLayout()


        val token = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE).getString("token", null)

        if (token != null) {
            presenter = VendaPresenter(this)
            presenter.getVendas(token)
        }



        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabs.selectTab(binding.tabs.getTabAt(position))
            }
        })

    }

     private fun setupTabLayout() {

        binding.tabs.addTab(binding.tabs.newTab())
        binding.tabs.addTab(binding.tabs.newTab())
        binding.tabs.getTabAt(0)?.setIcon(R.drawable.graph)
        binding.tabs.getTabAt(1)?.setIcon(R.drawable.creditcardicon)
        binding.tabs.getTabAt(0)!!.icon!!.colorFilter =
            BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                ContextCompat.getColor(
                    this,
                    R.color.white
                ), BlendModeCompat.SRC_IN
            )
        binding.tabs.getTabAt(1)!!.icon!!.colorFilter =
            BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                ContextCompat.getColor(
                    this,
                    R.color.white
                ), BlendModeCompat.SRC_IN
            )
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.pager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }

    inner class SlidePagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fm, lifecycle) {
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return if (position == 0) {
                graphicFragment
            } else {
                cardFragment
            }
        }

    }

   override fun mostraVendas(vendas: ArrayList<Venda>) {
        val recyclerVendas = binding.recyclerVendas

        adapterVenda = AdapterMovimentacao(vendas)

        recyclerVendas.layoutManager = LinearLayoutManager(this)
        recyclerVendas.adapter = adapterVenda

    }




}