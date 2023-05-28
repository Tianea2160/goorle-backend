package com.jaranda.goorlebackend.web.comment.controller

import com.jaranda.goorlebackend.domain.comment.service.CommentService
import io.swagger.v3.oas.annotations.Operation
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
    @Operation(tags = ["댓글"], summary = "최근 댓글 조회", description = "최근 작성된 댓글 6개를 조회합니다.")
    fun findRecentComments() = commentService.findRecentComments()

    @GetMapping("/me")
    @Operation(tags = ["댓글"], summary = "내가 쓴 댓글 전체 조회", description = "내가 쓴 댓글을 전체 조회합니다.")
    fun findMyComments(authentication: Authentication) = commentService.findMyComments(authentication.name)
}