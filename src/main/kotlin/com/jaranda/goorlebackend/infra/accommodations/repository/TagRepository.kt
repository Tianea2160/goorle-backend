package com.jaranda.goorlebackend.infra.accommodations.repository

import com.jaranda.goorlebackend.domain.accommodations.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository : JpaRepository<Tag, String>