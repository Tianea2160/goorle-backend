package com.jaranda.goorlebackend.domain.user.service

import com.jaranda.goorlebackend.domain.user.dto.UserReadDTO
import com.jaranda.goorlebackend.domain.user.entity.User
import com.jaranda.goorlebackend.domain.user.error.UserAlreadyException
import com.jaranda.goorlebackend.domain.user.error.UserNotFoundException
import com.jaranda.goorlebackend.infra.user.adapter.UserAdapter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userAdapter: UserAdapter,
) {
    @Transactional
    fun createUser(userId: String): UserReadDTO {
        if (userAdapter.existsById(userId)) throw UserAlreadyException()
        val user = User(id = userId)
        userAdapter.save(user)
        return UserReadDTO.of(user)
    }

    @Transactional(readOnly = true)
    fun findUserByIdOrNull(userId: String): UserReadDTO? = userAdapter.findByIdOrNull(userId)?.let {
        return UserReadDTO.of(it)
    }

    @Transactional(readOnly = true)
    fun findUserById(userId: String): UserReadDTO =
        (userAdapter.findByIdOrNull(userId) ?: throw UserNotFoundException()).let { return UserReadDTO.of(it) }

    @Transactional(readOnly = true)
    fun findGoorlePickers() = userAdapter.findGoorlePickers().map { UserReadDTO.of(it) }

    @Transactional
    fun updateNickname(loginId: String, nickname: String): String {
        val user = userAdapter.findByIdOrNull(loginId) ?: throw UserNotFoundException()
        user.nickname = nickname
        return nickname
    }

    @Transactional
    fun deleteUser(userId: String): String {
        val user = userAdapter.findByIdOrNull(userId) ?: throw UserNotFoundException()
        userAdapter.deleteById(userId)
        return userId
    }
}
