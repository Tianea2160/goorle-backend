package com.jaranda.goorlebackend.domain.accommodations.service

import com.jaranda.goorlebackend.domain.accommodations.dto.AccommodationCreateDTO
import com.jaranda.goorlebackend.domain.accommodations.dto.AccommodationReadDTO
import com.jaranda.goorlebackend.domain.accommodations.entity.Feature
import com.jaranda.goorlebackend.domain.accommodations.entity.Tag
import com.jaranda.goorlebackend.domain.accommodations.error.AccommodationNotFoundException
import com.jaranda.goorlebackend.domain.accommodations.error.FileSizeValidException
import com.jaranda.goorlebackend.domain.accommodations.error.NoPermissionException
import com.jaranda.goorlebackend.domain.image.entity.Image
import com.jaranda.goorlebackend.domain.image.service.ImageService
import com.jaranda.goorlebackend.domain.user.error.UserNotFoundException
import com.jaranda.goorlebackend.infra.accommodations.adapter.AccommodationAdapter
import com.jaranda.goorlebackend.infra.user.adapter.UserAdapter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class AccommodationService(
    private val accommodationAdapter: AccommodationAdapter,
    private val userAdapter: UserAdapter,
    private val imageService: ImageService
) {
    @Transactional(readOnly = true)
    fun findAll() = accommodationAdapter.findAll().map { AccommodationReadDTO.of(it) }

    @Transactional
    fun createAccommodation(
        loginId: String,
        create: AccommodationCreateDTO,
        files: List<MultipartFile>
    ): AccommodationReadDTO {
        if (files.isEmpty() || files.size > 10) throw FileSizeValidException()

        val user = userAdapter.findByIdOrNull(loginId) ?: throw UserNotFoundException()
        val imageUrls = imageService.uploadImages(files)
        val accommodation = accommodationAdapter.save(create.toEntity(user))
        user.score += 1

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
            accommodationAdapter.findByIdOrNull(accommodationId) ?: throw AccommodationNotFoundException()

        for (image in accommodation.images)
            imageService.deleteImage(image.url)

        if (accommodation.writer.id != loginId) throw NoPermissionException()
        accommodationAdapter.delete(accommodation)
        return accommodationId
    }

    @Transactional(readOnly = true)
    fun findAllByUserId(userId: String): List<AccommodationReadDTO> =
        accommodationAdapter.findAllByUserId(userId).map { AccommodationReadDTO.of(it) }

    @Transactional(readOnly = true)
    fun findById(accommodationId: String): AccommodationReadDTO {
        val accommodation =
            accommodationAdapter.findByIdOrNull(accommodationId) ?: throw AccommodationNotFoundException()
        return AccommodationReadDTO.of(accommodation)
    }
}