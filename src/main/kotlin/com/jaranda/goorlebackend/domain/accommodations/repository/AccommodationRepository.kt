package com.jaranda.goorlebackend.domain.accommodations.repository

import com.jaranda.goorlebackend.domain.accommodations.entity.Accommodation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccommodationRepository  : JpaRepository<Accommodation, String>