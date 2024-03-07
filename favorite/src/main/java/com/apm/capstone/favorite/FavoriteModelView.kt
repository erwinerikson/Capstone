package com.apm.capstone.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.apm.capstone.core.domain.usecase.UserUseCase

class FavoriteModelView(userUseCase: UserUseCase) : ViewModel() {

    val user = userUseCase.getFavoriteUser().asLiveData()
}