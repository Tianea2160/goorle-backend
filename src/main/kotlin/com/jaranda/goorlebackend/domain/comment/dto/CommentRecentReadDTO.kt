package com.jaranda.goorlebackend.domain.comment.dto

import com.jaranda.goorlebackend.domain.comment.entity.Comment
import com.jaranda.goorlebackend.domain.user.dto.UserReadDTO
import java.time.LocalDateTime


class CommentRecentReadDTO(
    val commentId: String,
    val accommodationId: String,
    val content: String,
    val writer: UserReadDTO,
    val emoticon: Int,
    val createdAt: LocalDateTime
) {
    companion object {
        fun of(entity: Comment) = CommentRecentReadDTO(
            commentId = entity.id,
            accommodationId = entity.accommodation.id,
            content = entity.content,
            writer = UserReadDTO.of(entity.writer),
            emoticon = entity.emoticon,
            createdAt = entity.createdAt,
        )
    }
}