package com.jaranda.goorlebackend.domain.comment.service

import com.jaranda.goorlebackend.domain.accommodations.error.AccommodationNotFoundException
import com.jaranda.goorlebackend.domain.accommodations.error.NoPermissionException
import com.jaranda.goorlebackend.domain.accommodations.repository.AccommodationRepository
import com.jaranda.goorlebackend.domain.comment.dto.CommentCreateDTO
import com.jaranda.goorlebackend.domain.comment.dto.CommentRecentReadDTO
import com.jaranda.goorlebackend.domain.comment.repository.CommentRepository
import com.jaranda.goorlebackend.domain.user.error.UserNotFoundException
import com.jaranda.goorlebackend.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val accommodationRepository: AccommodationRepository
) {

    @Transactional
    fun createComment(loginId: String, accommodationId: String, create: CommentCreateDTO): String {
        val user = userRepository.findByIdOrNull(loginId) ?: throw UserNotFoundException()
        val accommodation =
            accommodationRepository.findByIdOrNull(accommodationId) ?: throw AccommodationNotFoundException()

        val comment = create.toEntity(user, accommodation)
        commentRepository.save(comment)
        return comment.id
    }

    @Transactional
    fun deleteComment(loginId: String, accommodationId: String, commentId: String): String {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw AccommodationNotFoundException()
        if (loginId != comment.writer.id) throw NoPermissionException()
        commentRepository.deleteById(commentId)
        return commentId
    }

    @Transactional
    fun findRecentComments() = commentRepository.findRecentComments().map { CommentRecentReadDTO.of(it) }

    @Transactional(readOnly = true)
    fun findMyComments(loginId: String) = commentRepository.findMyComments(loginId).map { CommentRecentReadDTO.of(it) }
}