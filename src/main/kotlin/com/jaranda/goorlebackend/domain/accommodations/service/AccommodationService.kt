package com.jaranda.goorlebackend.domain.accommodations.service

import com.jaranda.goorlebackend.domain.accommodations.dto.AccommodationCreateDTO
import com.jaranda.goorlebackend.domain.accommodations.dto.AccommodationReadDTO
import com.jaranda.goorlebackend.domain.accommodations.entity.Feature
import com.jaranda.goorlebackend.domain.accommodations.entity.Tag
import com.jaranda.goorlebackend.domain.accommodations.repository.AccommodationRepository
import com.jaranda.goorlebackend.domain.user.error.UserNotFoundException
import com.jaranda.goorlebackend.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccommodationService(
    private val accommodationRepository: AccommodationRepository,
    private val userRepository: UserRepository,
){
    @Transactional(readOnly = true)
    fun findAll() = accommodationRepository.findAll().map { AccommodationReadDTO.of(it) }

    @Transactional
    fun createAccommodation(loginId :String,create : AccommodationCreateDTO): AccommodationReadDTO {
        val user = userRepository.findByIdOrNull(loginId) ?: throw UserNotFoundException()
        val accommodation = accommodationRepository.save(create.toEntity(user))

        for(value in create.tags)
            accommodation.tags.add(Tag(value = value, accommodation = accommodation))
        for(value in create.features)
            accommodation.features.add(Feature(value = value, accommodation = accommodation))

        return AccommodationReadDTO.of(accommodation)
    }
}