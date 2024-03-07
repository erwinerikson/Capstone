package com.apm.capstone.core.domain.usecase

import com.apm.capstone.core.domain.model.User
import com.apm.capstone.core.domain.repository.IUserRepository

@Suppress(*arrayOf("unused", "RedundantSuppression"))
class UserInteractor(private val userRepository: IUserRepository) : UserUseCase {

    override fun getAllUser() = userRepository.getAllUser()

    override fun getFavoriteUser() = userRepository.getFavoriteUser()

    override fun setFavoriteUser(user: User, favorite: Boolean): Boolean =
        userRepository.setFavoriteUser(user, favorite)
}