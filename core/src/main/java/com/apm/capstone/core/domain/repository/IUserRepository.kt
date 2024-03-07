package com.apm.capstone.core.domain.repository

import com.apm.capstone.core.data.Resource
import com.apm.capstone.core.domain.model.User
import kotlinx.coroutines.flow.Flow

@Suppress("SameReturnValue")
interface IUserRepository {

    fun getAllUser(): Flow<Resource<List<User>>>

    fun getFavoriteUser(): Flow<List<User>>

    fun setFavoriteUser(user: User, favorite: Boolean): Boolean
}