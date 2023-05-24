package com.jaranda.goorlebackend.web.auth.controller

import com.jaranda.goorlebackend.domain.auth.service.AuthService
import com.jaranda.goorlebackend.web.auth.dto.LoginRequest
import com.jaranda.goorlebackend.web.auth.dto.LoginResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun login(@RequestBody login: LoginRequest) = LoginResponse.of(authService.login(login.toDTO()))
}
