package com.apm.capstone.core.domain.usecase

import com.apm.capstone.core.data.Resource
import com.apm.capstone.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getAllUser(): Flow<Resource<List<User>>>
    fun getFavoriteUser(): Flow<List<User>>
    fun setFavoriteUser(user: User, favorite: Boolean): Boolean
}