package com.jaranda.goorlebackend.infra.comment.adapter

import com.jaranda.goorlebackend.domain.comment.entity.Comment
import com.jaranda.goorlebackend.infra.comment.repository.CommentRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CommentAdapter(
    private val commentRepository: CommentRepository
) {
    fun save(entity: Comment) = commentRepository.save(entity)
    fun deleteById(id: String) = commentRepository.deleteById(id)
    fun findRecentComments() = commentRepository.findRecentComments()
    fun findMyComments(userId: String) = commentRepository.findMyComments(userId)
    fun findByIdOrNull(id: String) = commentRepository.findByIdOrNull(id)
}