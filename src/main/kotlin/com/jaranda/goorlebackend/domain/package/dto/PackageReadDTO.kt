package com.jaranda.goorlebackend.domain.`package`.dto

import com.jaranda.goorlebackend.domain.`package`.entity.Package

class PackageReadDTO(
    val id: String,
    val name: String,
    val content: String,
    val url: String,
) {
    companion object {
        fun of(entity: Package) = PackageReadDTO(
            id = entity.id,
            name = entity.name,
            content = entity.content,
            url = entity.url
        )
    }
}