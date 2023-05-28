package com.jaranda.goorlebackend.domain.`package`.dto

import com.jaranda.goorlebackend.domain.`package`.entity.Package

class PackageReadDTO(
    val packageId: String,
    val name: String,
    val content: String,
    val imageUrl : String,
    val url: String,
) {
    companion object {
        fun of(entity: Package) = PackageReadDTO(
            packageId = entity.id,
            name = entity.name,
            content = entity.content,
            imageUrl = entity.imageUrl,
            url = entity.url,
        )
    }
}