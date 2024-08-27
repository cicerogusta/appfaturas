package com.example.faturas_app.di

import com.example.faturas_app.network.api.RetrofitService
import com.example.faturas_app.repo.HomeRepository
import com.example.faturas_app.repo.LoginRepository
import com.example.faturas_app.viewmodel.home.HomeViewModel
import com.example.faturas_app.viewmodel.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val retrofitService by lazy {
    RetrofitService.getInstance()
}

val mainModule = module {
    // Separando as instâncias de HomeRepository e LoginRepository
    factory {
        HomeRepository(retrofitService)
    }

    factory {
        LoginRepository(retrofitService)
    }

    // Registrando os ViewModels no Koin
    viewModel {
        HomeViewModel(
            repository = get()
        )
    }

    viewModel {
        LoginViewModel(
            repository = get()
        )
    }
}
