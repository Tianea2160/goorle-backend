package com.jaranda.goorlebackend.domain.comment.entity

import com.jaranda.goorlebackend.domain.accommodations.entity.Accommodation
import com.jaranda.goorlebackend.domain.user.entity.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "comments")
class Comment(
    @Id
    val id: String = UUID.randomUUID().toString(),
    @ManyToOne(targetEntity = User::class)
    val writer: User,
    @Column(columnDefinition = "text")
    val content: String,
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne(targetEntity = Accommodation::class)
    val accommodation: Accommodation,
)