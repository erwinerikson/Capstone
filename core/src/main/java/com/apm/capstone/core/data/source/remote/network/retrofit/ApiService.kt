package com.apm.capstone.core.data.source.remote.network.retrofit

import com.apm.capstone.core.data.source.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/search/users")
    suspend fun getAllUser(@Query("q") query: String): UserResponse
}