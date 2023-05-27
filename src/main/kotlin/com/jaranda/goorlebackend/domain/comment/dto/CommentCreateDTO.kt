package com.jaranda.goorlebackend.domain.comment.dto

import com.jaranda.goorlebackend.domain.accommodations.entity.Accommodation
import com.jaranda.goorlebackend.domain.comment.entity.Comment
import com.jaranda.goorlebackend.domain.user.entity.User

class CommentCreateDTO(
    val content: String,
    val emoticon: Int,
) {
    fun toEntity(user: User, accommodation: Accommodation) =
        Comment(
            writer = user,
            content = content,
            emoticon = emoticon,
            accommodation = accommodation
        )
}