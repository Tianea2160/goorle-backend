package com.jaranda.goorlebackend.domain.comment.dto

import com.jaranda.goorlebackend.domain.comment.entity.Comment
import com.jaranda.goorlebackend.domain.user.dto.UserReadDTO
import java.time.LocalDateTime

class CommentReadDTO(
    val id: String,
    val content: String,
    val writer: UserReadDTO,
    val createdAt: LocalDateTime
) {
    companion object {
        fun of(entity: Comment): CommentReadDTO = CommentReadDTO(
            id = entity.id,
            content = entity.content,
            writer = UserReadDTO.of(entity.writer),
            createdAt = entity.createdAt
        )
    }
}



