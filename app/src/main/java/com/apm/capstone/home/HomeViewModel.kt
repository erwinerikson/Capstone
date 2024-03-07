package com.apm.capstone.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.apm.capstone.core.domain.usecase.UserUseCase

class HomeViewModel(userUseCase: UserUseCase) : ViewModel() {

    var user = userUseCase.getAllUser().asLiveData()
}