package com.example.faturas_app.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.faturas_app.repo.HomeRepository

class HomeViewModelFactory constructor(private val repository: HomeRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(this.repository) as T

        } else {
            throw IllegalAccessException("ViewModel not Found")
        }
    }


}