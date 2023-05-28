package com.jaranda.goorlebackend.web.user.controller

import com.jaranda.goorlebackend.domain.user.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/rank")
    fun findGoorlePickers() = userService.findGoorlePickers()

    @GetMapping("/me")
    fun findMyInfo(authentication: Authentication) = userService.findUserById(authentication.name)

    @GetMapping("/{userId}")
    fun findUserById(@PathVariable(name = "userId") userId: String) = userService.findUserById(userId)
}