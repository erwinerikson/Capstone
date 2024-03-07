package com.apm.capstone.core.data.source.remote

import android.util.Log
import com.apm.capstone.core.data.source.remote.network.ApiResponse
import com.apm.capstone.core.data.source.remote.network.retrofit.ApiService
import com.apm.capstone.core.data.source.remote.response.ItemsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllUser(): Flow<ApiResponse<List<ItemsItem>>> {
        return flow {
            try {
                val response = apiService.getAllUser(USER)
                val dataArray = response.items
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.items))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        const val USER = "sandra"
        const val TAG = "RemoteDataSource"
    }
}