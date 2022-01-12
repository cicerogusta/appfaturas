package com.example.faturas_app.ui.activities.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.faturas_app.R
import com.example.faturas_app.adapter.AdapterVendas
import com.example.faturas_app.adapter.MyPagerAdapter
import com.example.faturas_app.contract.HomeContract
import com.example.faturas_app.databinding.ActivityHomeBinding
import com.example.faturas_app.model.apiModel.Venda
import com.example.faturas_app.presenter.Presenter
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity(), HomeContract.View.HomeView {

    lateinit var binding: ActivityHomeBinding
    lateinit var adapterVenda: AdapterVendas

    lateinit var presenter: Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pager.adapter = MyPagerAdapter(supportFragmentManager, lifecycle)
        setupTabLayout()
        pageChangeCallBack()


        presenter = Presenter(homeView = this, context = this)
        presenter.getListaVendas()

        val refreshListener = SwipeRefreshLayout.OnRefreshListener {
            binding.swiperefresh.isRefreshing = true
            presenter.getListaVendas()

        }

        binding.swiperefresh.setOnRefreshListener(refreshListener)


    }


    override fun mostraVendas(vendas: ArrayList<Venda>) {
        val recyclerVendas = binding.recyclerVendas

        adapterVenda = AdapterVendas(vendas)

        recyclerVendas.layoutManager = LinearLayoutManager(this)
        recyclerVendas.adapter = adapterVenda

    }

    override fun pageChangeCallBack() {
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabs.selectTab(binding.tabs.getTabAt(position))
            }
        })
    }

    override fun setupTabLayout() {

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

    override fun getComponentHomeBinding(): ActivityHomeBinding {
        return binding
    }


}




