package com.jaranda.goorlebackend.domain.user.dto

import com.jaranda.goorlebackend.domain.user.entity.User


class UserReadDTO(
    val userId: String,
    val nickname: String,
    val score: Long,
    val level: Int,
) {
    companion object {
        fun of(user: User) = UserReadDTO(
            userId = user.id,
            nickname = user.nickname,
            score = user.score,
            level = calculateLevel(score = user.score)
        )
    }
}

fun calculateLevel(score: Long) = when (score) {
    in 0..2 -> 1
    in 3..7 -> 2
    in 8..14 -> 3
    in 15..23 -> 4
    else -> 1
}