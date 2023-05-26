package com.jaranda.goorlebackend.domain.image.entity

import com.jaranda.goorlebackend.domain.accommodations.entity.Accommodation
import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "images")
class Image(
    @Id
    @Column(name = "image_id")
    val id:String = UUID.randomUUID().toString(),
    val url : String,
    @ManyToOne(targetEntity = Accommodation::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    val accommodation: Accommodation
)