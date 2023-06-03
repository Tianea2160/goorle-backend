package com.jaranda.goorlebackend.domain.comment.service

import com.jaranda.goorlebackend.domain.accommodations.error.AccommodationNotFoundException
import com.jaranda.goorlebackend.domain.accommodations.error.NoPermissionException
import com.jaranda.goorlebackend.domain.comment.dto.CommentCreateDTO
import com.jaranda.goorlebackend.domain.comment.dto.CommentRecentReadDTO
import com.jaranda.goorlebackend.domain.user.error.UserNotFoundException
import com.jaranda.goorlebackend.infra.accommodations.adapter.AccommodationAdapter
import com.jaranda.goorlebackend.infra.comment.adapter.CommentAdapter
import com.jaranda.goorlebackend.infra.user.adapter.UserAdapter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val commentAdapter: CommentAdapter,
    private val userAdapter: UserAdapter,
    private val accommodationAdapter: AccommodationAdapter,
) {

    @Transactional
    fun createComment(loginId: String, accommodationId: String, create: CommentCreateDTO): String {
        val user = userAdapter.findByIdOrNull(loginId) ?: throw UserNotFoundException()
        val accommodation =
            accommodationAdapter.findByIdOrNull(accommodationId) ?: throw AccommodationNotFoundException()

        val comment = create.toEntity(user, accommodation)
        commentAdapter.save(comment)
        return comment.id
    }

    @Transactional
    fun deleteComment(loginId: String, accommodationId: String, commentId: String): String {
        val comment = commentAdapter.findByIdOrNull(commentId) ?: throw AccommodationNotFoundException()
        if (loginId != comment.writer.id) throw NoPermissionException()
        commentAdapter.deleteById(commentId)
        return commentId
    }

    @Transactional
    fun findRecentComments() = commentAdapter.findRecentComments().map { CommentRecentReadDTO.of(it) }

    @Transactional(readOnly = true)
    fun findMyComments(loginId: String) = commentAdapter.findMyComments(loginId).map { CommentRecentReadDTO.of(it) }
}