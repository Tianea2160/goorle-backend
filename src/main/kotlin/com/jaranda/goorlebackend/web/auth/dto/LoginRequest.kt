package com.jaranda.goorlebackend.web.auth.dto

import com.jaranda.goorlebackend.domain.auth.dto.LoginDTO


class LoginRequest(
    val provider: String,
    val identifier: String
){
    fun toDTO() : LoginDTO = LoginDTO(
        provider = provider,
        identifier = identifier
    )
}
