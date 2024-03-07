package com.apm.capstone.core.data.source.local

import com.apm.capstone.core.data.source.local.entity.UserEntity
import com.apm.capstone.core.data.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val userDao: UserDao) {

    fun getAllUser(): Flow<List<UserEntity>> = userDao.getAllUser()

    fun getFavoriteUser(): Flow<List<UserEntity>> = userDao.getFavoriteUser()

    suspend fun insertUser(userList: List<UserEntity>) = userDao.insertUser(userList)

    fun setFavoriteUser(user: UserEntity, favorite: Boolean) {
        user.isFavorite = favorite
        userDao.updateFavoriteUser(user)
    }
}