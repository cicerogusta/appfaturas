package com.example.faturas_app.ui.activities.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.faturas_app.R
import com.example.faturas_app.adapter.AdapterVendas
import com.example.faturas_app.adapter.MyPagerAdapter
import com.example.faturas_app.databinding.ActivityHomeBinding
import com.example.faturas_app.model.Venda
import com.example.faturas_app.network.api.RetrofitService
import com.example.faturas_app.repo.HomeRepository
import com.example.faturas_app.ui.activities.home.fragments.FirstFragment
import com.example.faturas_app.viewmodel.home.HomeViewModel
import com.example.faturas_app.viewmodel.home.HomeViewModelFactory
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.tabs.TabLayout
import java.lang.Exception

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    lateinit var viewModel: HomeViewModel

    private val retrofitService = RetrofitService.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =
            ViewModelProvider(
                this,
                HomeViewModelFactory(HomeRepository((retrofitService)))
            )[HomeViewModel::class.java]
        pageChangeCallBack()
        supportActionBar?.hide()
        setupTabLayout()


    }


    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart iniciado!", Toast.LENGTH_LONG).show()




        try {
            viewModel.liveVenda.observe(this, {
                listarVendas(it)
                Toast.makeText(this, "Lista preenchida!", Toast.LENGTH_LONG).show()
                setGrafico(it)

            })

            viewModel.errorMessage.observe(this, {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            })
        } catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun setGrafico(listaVenda: List<Venda>) {
        FirstFragment().onStart()
        val listaVendas = ArrayList<Venda>()

        val yValues = ArrayList<BarEntry>()
        val labels = ArrayList<String>()
        listaVenda.forEach {
            listaVendas.add(it)
            it.valor?.let { it1 -> BarEntry(listaVendas.size.toFloat(), it1.toFloat()) }
                ?.let { it2 ->
                    yValues.add(
                        it2
                    )
                }
            labels.add(Venda().formataDataVendaEspecifica(it.date))
            FirstFragment().setData(labels, yValues)


        }


    }

    override fun onResume() {
        super.onResume()
        viewModel.getVendas()
    }


    private fun configActionBar() {
        supportActionBar?.hide()
    }

    private fun listarVendas(vendas: List<Venda>) {
        val recyclerVendas = binding.recyclerVendas

        val adapterVenda = AdapterVendas(vendas)

        recyclerVendas.layoutManager = LinearLayoutManager(this)
        recyclerVendas.adapter = adapterVenda

    }

    private fun pageChangeCallBack() {
        binding.pager.adapter = MyPagerAdapter(supportFragmentManager, lifecycle)
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabs.selectTab(binding.tabs.getTabAt(position))
            }
        })
    }

    private fun setupTabLayout() {

        addTabs()
        setIconTabs()
        setIconColorTabs()
        setTabListener()
    }

    private fun setTabListener() {
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

    private fun setIconColorTabs() {
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
    }

    private fun setIconTabs() {
        binding.tabs.getTabAt(0)?.setIcon(R.drawable.graph)
        binding.tabs.getTabAt(1)?.setIcon(R.drawable.creditcardicon)
    }

    private fun addTabs() {
        binding.tabs.addTab(binding.tabs.newTab())
        binding.tabs.addTab(binding.tabs.newTab())
    }


    fun createAlertDialog(titulo: String, mensagem: String, needButton: Boolean) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(titulo)
        dialog.setMessage(mensagem)
        if (needButton) {
            dialog.setPositiveButton(
                "OK"
            ) { _, _ -> finish() }
        }


//        dialog.setPositiveButton("OK", null)
        dialog.setCancelable(true)
        dialog.create().show()

    }


}




