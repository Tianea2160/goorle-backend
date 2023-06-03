package com.jaranda.goorlebackend.infra.image.repository

import com.jaranda.goorlebackend.domain.image.entity.Image
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageRepository  : JpaRepository<Image, String>