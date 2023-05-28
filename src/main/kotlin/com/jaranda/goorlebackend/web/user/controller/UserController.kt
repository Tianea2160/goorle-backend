package com.jaranda.goorlebackend.web.user.controller

import com.jaranda.goorlebackend.domain.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/rank")
    @Operation(tags = ["유저"], summary = "구를 피커 조회", description = "스코어가 높은 기준으로 구를 피커 6명 조회합니다.")
    fun findGoorlePickers() = userService.findGoorlePickers()

    @GetMapping("/me")
    @Operation(tags = ["유저"], summary = "내 프로필 조회", description = "내 닉네임과 현재 스코어, 그리고 구르리 레벨을 표시합니다.")
    fun findMyInfo(authentication: Authentication) = userService.findUserById(authentication.name)

    @GetMapping("/{userId}")
    @Operation(tags = ["유저"], summary = "다른 사람 프로필 조회", description = "다른사람 닉네임과 현재 스코어, 그리고 구르리 레벨을 표시합니다.")
    fun findUserById(@PathVariable(name = "userId") userId: String) = userService.findUserById(userId)

    @PutMapping("/me/nickname")
    @Operation(tags = ["유저"], summary = "닉네임 변경", description = "나의 닉네임을 변경합니다.")
    fun updateNickname(
        authentication: Authentication,
        @RequestParam(name = "nickname") nickname: String,
    ) = userService.updateNickname(authentication.name, nickname)
}