package com.jaranda.goorlebackend.domain.user.dto

import com.jaranda.goorlebackend.domain.user.entity.User


class UserReadDTO(
    val userId: String,
) {
    companion object {
        fun of(user: User) = UserReadDTO(user.id)
    }
}