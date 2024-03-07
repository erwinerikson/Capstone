package com.apm.capstone.core.utils

import com.apm.capstone.core.data.source.local.entity.UserEntity
import com.apm.capstone.core.data.source.remote.response.ItemsItem
import com.apm.capstone.core.domain.model.User

object DataMapper {
    fun mapResponsesToEntities(input: List<ItemsItem>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val user = UserEntity(
                login = it.login,
                id = it.id,
                node_id = it.node_id,
                avatar_url = it.avatar_url,
                gravatar_id = it.gravatar_id,
                url = it.url,
                html_url = it.html_url,
                followers_url = it.followers_url,
                following_url = it.following_url,
                gists_url = it.gists_url,
                starred_url = it.starred_url,
                subscriptions_url = it.subscriptions_url,
                organizations_url = it.organizations_url,
                repos_url = it.repos_url,
                events_url = it.events_url,
                received_events_url = it.received_events_url,
                type = it.type,
                site_admin = it.site_admin,
                score = it.score
            )
            userList.add(user)
        }
        return userList
    }

    fun mapEntitiesToDomain(input: List<UserEntity>): List<User> =
        input.map {
            User(
                login = it.login,
                id = it.id,
                node_id = it.node_id,
                avatar_url = it.avatar_url,
                gravatar_id = it.gravatar_id,
                url = it.url,
                html_url = it.html_url,
                followers_url = it.followers_url,
                following_url = it.following_url,
                gists_url = it.gists_url,
                starred_url = it.starred_url,
                subscriptions_url = it.subscriptions_url,
                organizations_url = it.organizations_url,
                repos_url = it.repos_url,
                events_url = it.events_url,
                received_events_url = it.received_events_url,
                type = it.type,
                site_admin = it.site_admin,
                score = it.score,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: User) = UserEntity(
        login = input.login,
        id = input.id,
        node_id = input.node_id,
        avatar_url = input.avatar_url,
        gravatar_id = input.gravatar_id,
        url = input.url,
        html_url = input.html_url,
        followers_url = input.followers_url,
        following_url = input.following_url,
        gists_url = input.gists_url,
        starred_url = input.starred_url,
        subscriptions_url = input.subscriptions_url,
        organizations_url = input.organizations_url,
        repos_url = input.repos_url,
        events_url = input.events_url,
        received_events_url = input.received_events_url,
        type = input.type,
        site_admin = input.site_admin,
        score = input.score,
        isFavorite = input.isFavorite
    )
}