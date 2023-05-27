package com.jaranda.goorlebackend.web.`package`.controller

import com.jaranda.goorlebackend.domain.`package`.service.PackageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/packages")
class PackageController(
    private val packageService: PackageService
) {
    @GetMapping("")
    fun findAll() = packageService.findAll()
}