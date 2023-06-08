package com.jaranda.goorlebackend.domain.comment.dto

import com.jaranda.goorlebackend.domain.accommodations.dto.AccommodationReadDTO
import com.jaranda.goorlebackend.domain.accommodations.entity.Accommodation
import com.jaranda.goorlebackend.domain.comment.entity.Comment
import com.jaranda.goorlebackend.domain.user.dto.UserReadDTO
import java.time.LocalDateTime


class CommentSimpleReadDTO(
    val commentId: String,
    val content: String,
    val writer: UserReadDTO,
    val accommodation: AccommodationReadDTO,
    val createdAt: LocalDateTime
) {
    companion object {
        fun of(entity: Comment) = CommentSimpleReadDTO(
            commentId = entity.id,
            content = entity.content,
            writer = UserReadDTO.of(entity.writer),
            accommodation = AccommodationReadDTO.of(entity.accommodation),
            createdAt = entity.createdAt,
        )
    }
}


