package com.jaranda.goorlebackend.web.`package`.controller

import com.jaranda.goorlebackend.domain.`package`.service.PackageService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/packages")
class PackageController(
    private val packageService: PackageService
) {
    @GetMapping("")
    @Operation(tags = ["패키지"], summary = "패키지 전체 조회", description = "패키지 전체를 조회합니다.")
    fun findAll() = packageService.findAll()
}