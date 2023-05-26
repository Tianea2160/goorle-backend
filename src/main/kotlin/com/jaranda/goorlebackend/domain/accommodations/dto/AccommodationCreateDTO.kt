package com.jaranda.goorlebackend.domain.accommodations.dto

import com.jaranda.goorlebackend.domain.accommodations.entity.*
import com.jaranda.goorlebackend.domain.user.entity.User

class AccommodationCreateDTO(
    val name : String,
    val location : String,
    val position: Position,
    val tags : List<TagValue>,
    val features : List<FeatureValue>
){
    fun toEntity(user: User): Accommodation =
        Accommodation(location = location, user = user, name = name, position = position)
}