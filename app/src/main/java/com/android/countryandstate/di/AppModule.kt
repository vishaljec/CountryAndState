package com.android.countryandstate.di

import com.android.countryandstate.countrylist.viewmodel.CountryListViewModel
import com.android.countryandstate.statelist.viewmodel.StateListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { CountryListViewModel(get()) }

    viewModel { StateListViewModel(get()) }

    single { createCountryUseCase(get()) }

    single { createStateListUseCase(get()) }

    single { createRetrofitManager() }

    single { createCountryRepository(get(), get()) }

    single { createStateListRepository(get(), get()) }
}