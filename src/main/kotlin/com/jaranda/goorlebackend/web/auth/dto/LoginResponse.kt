package com.jaranda.goorlebackend.web.auth.dto

import com.jaranda.goorlebackend.domain.auth.dto.LoginSuccessDTO

class LoginResponse(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(dto: LoginSuccessDTO) = LoginResponse(dto.accessToken, dto.refreshToken)
    }
}