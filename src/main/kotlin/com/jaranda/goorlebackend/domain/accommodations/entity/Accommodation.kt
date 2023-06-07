package com.jaranda.goorlebackend.domain.accommodations.entity

import com.jaranda.goorlebackend.domain.comment.entity.Comment
import com.jaranda.goorlebackend.domain.image.entity.Image
import com.jaranda.goorlebackend.domain.user.entity.User
import com.jaranda.goorlebackend.shared.util.uuid
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "accommodations")
class Accommodation(
    @Id
    @Column(name = "accommodation_id")
    val id: String = uuid(),
    val name: String,
    @Embedded
    val position: Position,
    val location: String,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val writer: User,
    @OneToMany(mappedBy = "accommodation", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val comments: MutableList<Comment> = mutableListOf(),
    @OneToMany(mappedBy = "accommodation", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val images: MutableList<Image> = mutableListOf(),
    @OneToMany(mappedBy = "accommodation", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var tags: MutableList<Tag> = mutableListOf(),
    @OneToMany(mappedBy = "accommodation", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var features: MutableList<Feature> = mutableListOf(),
)

@Embeddable
class Position(val lng: Double, val lat: Double)


@Entity
@Table(name = "tags")
class Tag(
    @Id
    @Column(name = "tag_id")
    val id: String = uuid(),
    @Enumerated(EnumType.STRING)
    val value: TagValue,
    @ManyToOne(targetEntity = Accommodation::class, fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "accommodation_id")
    val accommodation: Accommodation
)

enum class TagValue {
    NATURE, OCEAN, DOG, HOTEL, TOUR, HANOK
}

@Entity
@Table(name = "features")
class Feature(
    @Id
    @Column(name = "feature_id")
    val id: String = uuid(),
    @Enumerated(EnumType.STRING)
    val value: FeatureValue,
    @ManyToOne(targetEntity = Accommodation::class, fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "accommodation_id")
    val accommodation: Accommodation,
)

enum class FeatureValue {
    PARKING, SLOPE, LIFT, ELEVATOR, RESTROOM, ESCALATOR, SHOWER, ACCOMMODATION
}