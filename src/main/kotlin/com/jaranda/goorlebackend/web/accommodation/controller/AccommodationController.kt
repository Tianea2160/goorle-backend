package com.jaranda.goorlebackend.web.accommodation.controller

import com.jaranda.goorlebackend.domain.accommodations.dto.AccommodationCreateDTO
import com.jaranda.goorlebackend.domain.accommodations.service.AccommodationService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/accommodations")
class AccommodationController(
    private val accommodationService: AccommodationService
){
    @PostMapping("")
    fun createAccommodation(authentication: Authentication, @RequestBody create : AccommodationCreateDTO) =
        accommodationService.createAccommodation(authentication.name, create)
}