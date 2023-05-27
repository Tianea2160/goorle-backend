package com.jaranda.goorlebackend.domain.`package`.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "packages")
class Package(
    @Id
    @Column(name = "package_id")
    val id: String = UUID.randomUUID().toString(),
    @Column(name = "name")
    val name: String,
    @Column(name = "content", columnDefinition = "text")
    val content: String,
    @Column(name = "image_url")
    val imageUrl : String,
    @Column(name = "url")
    val url: String,
)