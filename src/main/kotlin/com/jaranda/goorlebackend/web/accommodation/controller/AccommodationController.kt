package com.jaranda.goorlebackend.web.accommodation.controller

import com.jaranda.goorlebackend.domain.accommodations.dto.AccommodationCreateDTO
import com.jaranda.goorlebackend.domain.accommodations.service.AccommodationService
import com.jaranda.goorlebackend.domain.comment.dto.CommentCreateDTO
import com.jaranda.goorlebackend.domain.comment.service.CommentService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/accommodations")
class AccommodationController(
    private val accommodationService: AccommodationService,
    private val commentService: CommentService,
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

    @PostMapping("/{accommodationId}/comments")
    fun createComment(
        authentication: Authentication,
        @PathVariable("accommodationId") accommodationId: String,
        @RequestBody create: CommentCreateDTO,
    ) = commentService.createComment(authentication.name, accommodationId, create)

    @DeleteMapping("/{accommodationId}/comments/{commentId}")
    fun deleteComment(
        authentication: Authentication,
        @PathVariable("accommodationId") accommodationId: String,
        @PathVariable("commentId") commentId: String,
    ) = commentService.deleteComment(authentication.name, accommodationId, commentId)

}
