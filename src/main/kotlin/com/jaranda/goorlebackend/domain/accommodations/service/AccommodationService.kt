package com.jaranda.goorlebackend.domain.accommodations.service

import com.jaranda.goorlebackend.domain.accommodations.dto.AccommodationCreateDTO
import com.jaranda.goorlebackend.domain.accommodations.dto.AccommodationReadDTO
import com.jaranda.goorlebackend.domain.accommodations.entity.Feature
import com.jaranda.goorlebackend.domain.accommodations.entity.Tag
import com.jaranda.goorlebackend.domain.accommodations.error.AccommodationNotFoundException
import com.jaranda.goorlebackend.domain.accommodations.error.FileSizeValidException
import com.jaranda.goorlebackend.domain.accommodations.error.NoPermissionException
import com.jaranda.goorlebackend.domain.accommodations.repository.AccommodationRepository
import com.jaranda.goorlebackend.domain.image.entity.Image
import com.jaranda.goorlebackend.domain.image.service.ImageService
import com.jaranda.goorlebackend.domain.user.error.UserNotFoundException
import com.jaranda.goorlebackend.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class AccommodationService(
    private val accommodationRepository: AccommodationRepository,
    private val userRepository: UserRepository,
    private val imageService: ImageService
) {
    @Transactional(readOnly = true)
    fun findAll() = accommodationRepository.findAll().map { AccommodationReadDTO.of(it) }

    @Transactional
    fun createAccommodation(
        loginId: String,
        create: AccommodationCreateDTO,
        files: List<MultipartFile>
    ): AccommodationReadDTO {
        if (files.isEmpty() || files.size > 10) throw FileSizeValidException()

        val user = userRepository.findByIdOrNull(loginId) ?: throw UserNotFoundException()
        val imageUrls = imageService.uploadImages(files)
        val accommodation = accommodationRepository.save(create.toEntity(user))

        for (url in imageUrls)
            accommodation.images.add(Image(url = url, accommodation = accommodation))
        for (value in create.tags)
            accommodation.tags.add(Tag(value = value, accommodation = accommodation))
        for (value in create.features)
            accommodation.features.add(Feature(value = value, accommodation = accommodation))

        return AccommodationReadDTO.of(accommodation)
    }

    @Transactional
    fun deleteAccommodation(loginId: String, accommodationId: String): String {
        val accommodation =
            accommodationRepository.findByIdOrNull(accommodationId) ?: throw AccommodationNotFoundException()

        for (image in accommodation.images)
            imageService.deleteImage(image.url)

        if (accommodation.writer.id != loginId) throw NoPermissionException()
        accommodationRepository.delete(accommodation)
        return accommodationId
    }

    @Transactional(readOnly = true)
    fun findAllByUserId(userId: String): List<AccommodationReadDTO> =
        accommodationRepository.findAllByUserId(userId).map { AccommodationReadDTO.of(it) }

    @Transactional(readOnly = true)
    fun findById(accommodationId: String): AccommodationReadDTO {
        val accommodation =
            accommodationRepository.findByIdOrNull(accommodationId) ?: throw AccommodationNotFoundException()
        return AccommodationReadDTO.of(accommodation)
    }
}