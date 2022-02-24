package com.example.faturas_app.di

import com.example.faturas_app.network.api.RetrofitService
import com.example.faturas_app.repo.HomeRepository
import com.example.faturas_app.viewmodel.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val retrofitService by lazy {
    RetrofitService.getInstance()
}
val mainModule = module {
    factory {
        HomeRepository(retrofitService)
    }

    viewModel {
        HomeViewModel(
            repository = get()
        )
    }
}