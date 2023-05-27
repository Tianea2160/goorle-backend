package com.jaranda.goorlebackend.web.accommodation.controller

import com.jaranda.goorlebackend.domain.accommodations.dto.AccommodationCreateDTO
import com.jaranda.goorlebackend.domain.accommodations.service.AccommodationService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/accommodations")
class AccommodationController(
    private val accommodationService: AccommodationService
) {
    @GetMapping("")
    fun findAll() = accommodationService.findAll()

    @PostMapping("")
    fun createAccommodation(
        authentication: Authentication,
        @RequestPart("data") create: AccommodationCreateDTO,
        @RequestPart("images") files: List<MultipartFile>,
    ) = accommodationService.createAccommodation(authentication.name, create, files)

    @DeleteMapping("/{accommodationId}")
    fun deleteAccommodation(
        authentication: Authentication,
        @PathVariable("accommodationId") accommodationId: String
    ) = accommodationService.deleteAccommodation(authentication.name, accommodationId)
}
