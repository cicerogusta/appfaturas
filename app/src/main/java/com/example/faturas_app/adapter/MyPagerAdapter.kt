package com.example.faturas_app.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.faturas_app.ui.fragments.FirstFragment
import com.example.faturas_app.ui.fragments.SecondFragment

class MyPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }


    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            FirstFragment()
        } else {
            SecondFragment()
        }
    }

}
