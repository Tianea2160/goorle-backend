package com.jaranda.goorlebackend.domain.auth.service

import com.jaranda.goorlebackend.domain.auth.dto.LoginDTO
import com.jaranda.goorlebackend.domain.auth.dto.LoginSuccessDTO
import com.jaranda.goorlebackend.domain.user.service.UserService
import com.jaranda.goorlebackend.shared.security.JwtService
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration

@Service
class AuthService(
    private val userService: UserService,
    private val jwtService: JwtService,
    private val redisTemplate: StringRedisTemplate,
) {
    @Transactional
    fun login(dto: LoginDTO): LoginSuccessDTO {
        val userId = "${dto.provider}_${dto.identifier}"
        userService.findUserByIdOrNull(userId) ?: userService.createUser(userId)
        val accessToken = jwtService.createAccessToken(userId)
        val refreshToken = jwtService.createRefreshToken(userId)
        redisTemplate.opsForValue().set(userId, refreshToken, Duration.ofMillis(jwtService.refreshDuration))
        return LoginSuccessDTO(accessToken, refreshToken)
    }

    fun logout(userId: String) {
        redisTemplate.delete(userId)
    }

    fun secession(userId: String) {
        userService.deleteUser(userId)
    }
}

