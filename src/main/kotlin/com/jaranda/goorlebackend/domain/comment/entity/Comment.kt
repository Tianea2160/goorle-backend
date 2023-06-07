package com.jaranda.goorlebackend.domain.comment.entity

import com.jaranda.goorlebackend.domain.accommodations.entity.Accommodation
import com.jaranda.goorlebackend.domain.user.entity.User
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "comments")
class Comment(
    @Id
    @Column(name = "comment_id")
    val id: String = UUID.randomUUID().toString(),
    @ManyToOne(targetEntity = User::class)
    @JoinColumn(name = "writer_id")
    val writer: User,
    @Column(columnDefinition = "text")
    val content: String,
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne(targetEntity = Accommodation::class)
    @JoinColumn(name = "accommodation_id")
    val accommodation: Accommodation,
)