package com.apm.capstone.di

import com.apm.capstone.core.domain.usecase.UserInteractor
import com.apm.capstone.core.domain.usecase.UserUseCase
import com.apm.capstone.detail.DetailUserViewModel
import com.apm.capstone.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailUserViewModel(get()) }
}