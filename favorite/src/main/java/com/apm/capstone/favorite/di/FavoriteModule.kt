package com.apm.capstone.favorite.di

import com.apm.capstone.favorite.FavoriteModelView
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val favoriteModule = module {
    viewModel { FavoriteModelView(get()) }
}