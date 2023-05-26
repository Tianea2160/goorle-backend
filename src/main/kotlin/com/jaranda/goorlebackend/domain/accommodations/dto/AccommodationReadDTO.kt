package com.jaranda.goorlebackend.domain.accommodations.dto

import com.jaranda.goorlebackend.domain.accommodations.entity.Accommodation
import com.jaranda.goorlebackend.domain.accommodations.entity.FeatureValue
import com.jaranda.goorlebackend.domain.accommodations.entity.Position
import com.jaranda.goorlebackend.domain.accommodations.entity.TagValue

class AccommodationReadDTO(
    val id: String,
    val name: String,
    val location: String,
    val position: Position,
    val images: List<String>,
    val tags: List<TagValue>,
    val features: List<FeatureValue>
) {
    companion object {
        fun of(entity: Accommodation): AccommodationReadDTO = AccommodationReadDTO(
            id = entity.id,
            name = entity.name,
            location = entity.location,
            position = entity.position,
            images = entity.images.map { image -> image.url },
            tags = entity.tags.map { tag -> tag.value },
            features = entity.features.map { feature -> feature.value }
        )
    }
}