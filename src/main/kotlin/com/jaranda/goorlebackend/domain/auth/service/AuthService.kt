package com.jaranda.goorlebackend.domain.auth.service

import com.jaranda.goorlebackend.domain.auth.dto.LoginDTO
import com.jaranda.goorlebackend.domain.auth.dto.LoginSuccessDTO
import com.jaranda.goorlebackend.domain.user.service.UserService
import com.jaranda.goorlebackend.shared.security.JwtService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userService: UserService,
    private val jwtService: JwtService,
) {
    @Transactional
    fun login(dto: LoginDTO): LoginSuccessDTO {
        val userId = "${dto.provider}_${dto.identifier}"
        userService.findUserByIdOrNull(userId) ?: userService.createUser(userId)
        val accessToken = jwtService.createAccessToken(userId)
        val refreshToken = jwtService.createRefreshToken(userId)
        return LoginSuccessDTO(accessToken, refreshToken)
    }

    fun logout(userId: String) {
    }

    fun secession(userId:String){
        userService.deleteUser(userId)
    }
}

