package com.apm.capstone.utils

import com.apm.capstone.core.data.NetworkBoundResource
import com.apm.capstone.core.data.Resource
import com.apm.capstone.core.data.source.local.entity.UserEntity
import com.apm.capstone.core.data.source.remote.network.ApiResponse
import com.apm.capstone.core.data.source.remote.response.ItemsItem
import com.apm.capstone.core.domain.model.User
import com.apm.capstone.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map

@Suppress("CAST_NEVER_SUCCEEDS")
object DataDummy {
    fun generateDummyDataDetail(): User {
        return User(
            login = "arif",
            id = 6605876,
            node_id = "MDQ6VXNlcjY2MDU4NzY=",
            avatar_url = "https://avatars.githubusercontent.com/u/6605876?v=4",
            gravatar_id = "",
            url = "https://api.github.com/users/arif",
            html_url = "https://github.com/arif",
            followers_url = "https://api.github.com/users/arif/followers",
            following_url = "https://api.github.com/users/arif/following{/other_user}",
            gists_url = "https://api.github.com/users/arif/gists{/gist_id}",
            starred_url = "https://api.github.com/users/arif/starred{/owner}{/repo}",
            subscriptions_url = "https://api.github.com/users/arif/subscriptions",
            organizations_url = "https://api.github.com/users/arif/orgs",
            repos_url = "https://api.github.com/users/arif/repos",
            events_url = "https://api.github.com/users/arif/events{/privacy}",
            received_events_url = "https://api.github.com/users/arif/received_events",
            type = "User",
            site_admin = false,
            score = 1,
            isFavorite = false
        )
    }

    fun allUser(): Flow<List<UserEntity>> {
        val listUser: MutableList<List<UserEntity>> = arrayListOf()
        for (i in 1..10) {
            val user = UserEntity(
                "user + $i",
                i,
                "MDQ + $i",
                "https://api + $i",
                "",
                "https://api + $i",
                "https://api + $i",
                "https://api + $i",
                "https://api + $i",
                "https://api + $i",
                "https://api + $i",
                "https://api + $i",
                "https://api + $i",
                "https://api + $i",
                "https://api + $i",
                "https://api + $i",
                "User",
                false,
                1,
                false
            )
            listUser.add(listOf(user))
        }
        return listUser.asFlow()
    }

    fun noUser(): Flow<List<UserEntity>> {
        val listUser: MutableList<List<UserEntity>> = arrayListOf()
        return listUser.asFlow()
    }

    fun generateDummyListUser(): Flow<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<ItemsItem>>() {
            override fun loadFromDB(): Flow<List<User>> {
                return allUser().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ItemsItem>>> {
                return null as Flow<ApiResponse<List<ItemsItem>>>
            }

            override suspend fun saveCallResult(data: List<ItemsItem>) {
                return
            }

            override fun shouldFetch(data: List<User>?): Boolean =
                data.isNullOrEmpty()
        }.asFlow()

    fun generateDummyNoUser(): Flow<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<ItemsItem>>() {
            override fun loadFromDB(): Flow<List<User>> {
                return noUser().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ItemsItem>>> {
                return null as Flow<ApiResponse<List<ItemsItem>>>
            }

            override suspend fun saveCallResult(data: List<ItemsItem>) {
                return
            }

            override fun shouldFetch(data: List<User>?): Boolean =
                data.isNullOrEmpty()
        }.asFlow()


}