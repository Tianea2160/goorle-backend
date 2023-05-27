package com.jaranda.goorlebackend.domain.comment.repository

import com.jaranda.goorlebackend.domain.comment.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository  :JpaRepository<Comment, String>