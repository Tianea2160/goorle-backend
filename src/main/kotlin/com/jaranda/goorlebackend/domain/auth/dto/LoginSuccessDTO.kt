package com.jaranda.goorlebackend.domain.auth.dto


class LoginSuccessDTO(
    val accessToken: String,
    val refreshToken: String,
)