package com.apm.capstone.detail

import androidx.lifecycle.ViewModel
import com.apm.capstone.core.domain.model.User
import com.apm.capstone.core.domain.usecase.UserUseCase

@Suppress("BooleanMethodIsAlwaysInverted")
class DetailUserViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun setFavoriteUser(user: User, favorite: Boolean): Boolean {
        return userUseCase.setFavoriteUser(user, favorite)
    }
}