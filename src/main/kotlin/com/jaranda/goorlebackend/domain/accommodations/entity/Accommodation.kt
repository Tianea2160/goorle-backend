package com.jaranda.goorlebackend.domain.accommodations.entity

import com.jaranda.goorlebackend.domain.comment.entity.Comment
import com.jaranda.goorlebackend.domain.image.entity.Image
import com.jaranda.goorlebackend.domain.user.entity.User
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "accommodations")
class Accommodation(
    @Id
    @Column(name = "accommodation_id")
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val location: String,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    @OneToMany(mappedBy = "accommodation", fetch = FetchType.LAZY)
    val comments: List<Comment>,
    @OneToMany(mappedBy = "accommodation",  fetch = FetchType.LAZY)
    val images : List<Image>,
)


@Entity
@Table(name = "tags")
class Tag(
    @Id
    @Column(name = "tag_id")
    val id: String = UUID.randomUUID().toString(),
    @Enumerated(EnumType.STRING)
    val value: TagValue,
    @ManyToOne(targetEntity = Accommodation::class, fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "accommodation_id")
    val accommodation: Accommodation
)

enum class TagValue {
    NATURE, SEA, PET, RURAL, STAYCATION, ATTRACTION, HANOK
}

@Entity
@Table(name = "features")
class Feature(
    @Id
    @Column(name = "feature_id")
    val id: String = UUID.randomUUID().toString(),
    @Enumerated(EnumType.STRING)
    val value: FeatureValue,
    @ManyToOne(targetEntity = Accommodation::class, fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "accommodation_id")
    val accommodation: Accommodation,
)

enum class FeatureValue {
    PARKING, RUNWAY, LIFT, ELEVATOR, RESTROOM, ESCALATOR, SHOWER, GUESTROOM
}