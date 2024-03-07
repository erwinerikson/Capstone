package com.apm.capstone.core.data

import com.apm.capstone.core.data.source.local.LocalDataSource
import com.apm.capstone.core.data.source.remote.RemoteDataSource
import com.apm.capstone.core.data.source.remote.network.ApiResponse
import com.apm.capstone.core.data.source.remote.response.ItemsItem
import com.apm.capstone.core.domain.model.User
import com.apm.capstone.core.domain.repository.IUserRepository
import com.apm.capstone.core.utils.AppExecutors
import com.apm.capstone.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Suppress(*arrayOf("unused", "RedundantSuppression"))
class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IUserRepository {
    override fun getAllUser(): Flow<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<ItemsItem>>() {
            override fun loadFromDB(): Flow<List<User>> {
                return localDataSource.getAllUser().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<User>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ItemsItem>>> =
                remoteDataSource.getAllUser()

            override suspend fun saveCallResult(data: List<ItemsItem>) {
                val userList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUser(userList)
            }
        }.asFlow()

    override fun getFavoriteUser(): Flow<List<User>> {
        return localDataSource.getFavoriteUser().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteUser(user: User, favorite: Boolean): Boolean {
        val userEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute { localDataSource.setFavoriteUser(userEntity, favorite) }
        return true
    }
}