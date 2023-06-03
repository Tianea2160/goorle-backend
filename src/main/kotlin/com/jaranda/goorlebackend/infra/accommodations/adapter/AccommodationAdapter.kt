package com.jaranda.goorlebackend.infra.accommodations.adapter

import com.jaranda.goorlebackend.domain.accommodations.entity.Accommodation
import com.jaranda.goorlebackend.infra.accommodations.repository.AccommodationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class AccommodationAdapter(
    private val accommodationRepository: AccommodationRepository
) {
    fun findAllByUserId(userId: String) = accommodationRepository.findAllByUserId(userId)
    fun findByIdOrNull(id: String) = accommodationRepository.findByIdOrNull(id)
    fun save(entity: Accommodation) = accommodationRepository.save(entity)
    fun delete(entity: Accommodation) = accommodationRepository.delete(entity)
    fun findAll() = accommodationRepository.findAll()
}