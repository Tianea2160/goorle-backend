package com.jaranda.goorlebackend.infra.user.adapter

import com.jaranda.goorlebackend.domain.user.entity.User
import com.jaranda.goorlebackend.infra.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class UserAdapter(
    private val userRepository: UserRepository
) {
    fun save(entity: User) = userRepository.save(entity)
    fun findByIdOrNull(id: String) = userRepository.findByIdOrNull(id)
    fun findGoorlePickers() = userRepository.findGoorlePickers()
    fun existsById(id: String) = userRepository.existsById(id)
    fun deleteById(id: String) = userRepository.deleteById(id)
}