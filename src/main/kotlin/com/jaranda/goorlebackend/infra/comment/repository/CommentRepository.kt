package com.jaranda.goorlebackend.infra.comment.repository

import com.jaranda.goorlebackend.domain.comment.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, String> {
    @Query("SELECT c FROM Comment c ORDER BY c.createdAt DESC LIMIT 6")
    fun findRecentComments(): List<Comment>

    @Query("SELECT c FROM Comment c WHERE c.writer.id = :userId ORDER BY c.createdAt DESC")
    fun findMyComments(userId: String): List<Comment>
}