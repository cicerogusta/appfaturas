package com.example.faturas_app.ui.activities.home.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.faturas_app.R
import com.example.faturas_app.adapter.AdapterVendas
import com.example.faturas_app.adapter.MyPagerAdapter
import com.example.faturas_app.databinding.ActivityHomeBinding
import com.example.faturas_app.model.Venda
import com.example.faturas_app.viewmodel.home.HomeViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getVendas()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pageChangeCallBack()
        setupTabLayout()
        config()
        activity?.let { hideSoftKeyboard(it) }
    }
    private fun hideSoftKeyboard(activity: Activity) {
        if (activity.currentFocus == null){
            return
        }
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }

    fun config() {
        try {

            viewModel.liveVenda.observe(this, {
                listarVendas(it)
            })


            viewModel.errorMessage.observe(this, {
            })
        } catch (e: Exception) {
        }
    }


    private fun listarVendas(vendas: List<Venda>) {
        val recyclerVendas = binding.recyclerVendas

        val adapterVenda = AdapterVendas(vendas)

        recyclerVendas.layoutManager = LinearLayoutManager(requireContext())
        recyclerVendas.adapter = adapterVenda

    }

    private fun pageChangeCallBack() {
        binding.pager.adapter = MyPagerAdapter(childFragmentManager, lifecycle)
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
                    requireContext(),
                    R.color.white
                ), BlendModeCompat.SRC_IN
            )
        binding.tabs.getTabAt(1)!!.icon!!.colorFilter =
            BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                ContextCompat.getColor(
                    requireContext(),
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
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle(titulo)
        dialog.setMessage(mensagem)
        if (needButton) {
            dialog.setPositiveButton(
                "OK"
            ) { _, _ -> onDestroy() }
        }


//        dialog.setPositiveButton("OK", null)
        dialog.setCancelable(true)
        dialog.create().show()

    }


}




