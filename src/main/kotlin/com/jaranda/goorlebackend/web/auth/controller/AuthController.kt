package com.jaranda.goorlebackend.web.auth.controller

import com.jaranda.goorlebackend.domain.auth.service.AuthService
import com.jaranda.goorlebackend.shared.dto.CommonResponse
import com.jaranda.goorlebackend.web.auth.dto.LoginRequest
import com.jaranda.goorlebackend.web.auth.dto.LoginResponse
import io.swagger.v3.oas.annotations.Operation
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/login")
    @Operation(tags = ["인증/인가"], summary = "로그인", description = "기존에 없던 사용자는 자동 회원가입 후 로그인이 됩니다. 기존의 회원은 로그인만 됩니다.")
    fun login(@RequestBody login: LoginRequest) = LoginResponse.of(authService.login(login.toDTO()))

    @PostMapping("/logout")
    @Operation(tags = ["인증/인가"], summary = "로그아웃", description = "로그아웃을 진행합니다.")
    fun logout(authentication: Authentication): CommonResponse {
        authService.logout(authentication.name)
        return CommonResponse(
            code = "LOGOUT_SUCCESS",
            status = 200
        )
    }

    @DeleteMapping("/secession")
    @Operation(tags = ["인증/인가"], summary = "회원 탈퇴", description = "로그아웃 및 회원탈퇴가 진행합니다. 해당 유저와 관련된 모든 정보는 일괄 삭제됩니다.")
    fun secession(authentication: Authentication): CommonResponse {
        authService.logout(authentication.name)
        authService.secession(authentication.name)
        return CommonResponse(
            code = "SECESSION_SUCCESS",
            status = 200
        )
    }
}
