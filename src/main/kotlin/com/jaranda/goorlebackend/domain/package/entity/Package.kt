package com.jaranda.goorlebackend.domain.`package`.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "packages")
class Package(
    @Id
    @Column(name = "package_id")
    val id: String = UUID.randomUUID().toString(),
    val url: String,
)