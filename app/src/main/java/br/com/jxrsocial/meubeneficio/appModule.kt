package br.com.jxrsocial.meubeneficio

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { IncomeViewModel() }
}
