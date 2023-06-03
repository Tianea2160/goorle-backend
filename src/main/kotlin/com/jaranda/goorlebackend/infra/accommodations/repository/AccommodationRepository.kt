package com.jaranda.goorlebackend.infra.accommodations.repository

import com.jaranda.goorlebackend.domain.accommodations.entity.Accommodation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AccommodationRepository : JpaRepository<Accommodation, String> {
    @Query("SELECT a FROM Accommodation a WHERE a.writer.id = :userId")
    fun findAllByUserId(userId: String): List<Accommodation>
}