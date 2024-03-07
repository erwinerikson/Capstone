package com.apm.capstone.favorite.utils

import com.apm.capstone.core.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

object DataDummy {
    fun generateDummyListFavorite(): Flow<List<User>> {
        val listUser: MutableList<List<User>> = arrayListOf()
        for (i in 1..10) {
            val user = User(
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
}