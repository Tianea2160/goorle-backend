package com.jaranda.goorlebackend.web.comment.controller

import com.jaranda.goorlebackend.domain.comment.service.CommentService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/comments")
class CommentController(
    private val commentService: CommentService
) {
    @GetMapping("/recent")
    fun findRecentComments() = commentService.findRecentComments()

    @GetMapping("/me")
    fun findMyComments(authentication: Authentication) = commentService.findMyComments(authentication.name)
}