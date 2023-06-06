package com.jaranda.goorlebackend.domain.accommodations.service

import com.jaranda.goorlebackend.domain.accommodations.dto.AccommodationCreateDTO
import com.jaranda.goorlebackend.domain.accommodations.entity.Accommodation
import com.jaranda.goorlebackend.domain.accommodations.entity.FeatureValue
import com.jaranda.goorlebackend.domain.accommodations.entity.Position
import com.jaranda.goorlebackend.domain.accommodations.entity.TagValue
import com.jaranda.goorlebackend.domain.image.entity.Image
import com.jaranda.goorlebackend.domain.image.service.ImageService
import com.jaranda.goorlebackend.domain.user.entity.User
import com.jaranda.goorlebackend.infra.accommodations.adapter.AccommodationAdapter
import com.jaranda.goorlebackend.infra.user.adapter.UserAdapter
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class AccommodationServiceTest : BehaviorSpec({

    val accommodationAdapter: AccommodationAdapter = mockk()
    val userAdapter: UserAdapter = mockk()
    val imageService: ImageService = mockk()
    val accommodationService = AccommodationService(accommodationAdapter, userAdapter, imageService)
    val loginId = "test login id"
    val loginUser = User(
        id = loginId,
        nickname = "test nickname",
        score = 10,
        accommodations = listOf(),
        comments = listOf()
    )
    every { userAdapter.findByIdOrNull(loginId) } returns loginUser

    given("숙소를 생성에 대한 정보가 주어졌을 때") {
        val create = AccommodationCreateDTO(
            name = "accommodatino name",
            location = "location",
            position = Position(10.0, 20.0),
            features = listOf(FeatureValue.ELEVATOR, FeatureValue.PARKING),
            tags = listOf(TagValue.TOUR, TagValue.HANOK, TagValue.DOG)
        )
        `when`("숙소 생성을 요청하면") {
            every { accommodationAdapter.save(any()) } returnsArgument 0
            every { imageService.uploadImages(any()) } returns mutableListOf("test image url")

            val read =
                accommodationService.createAccommodation(loginId = loginId, create = create, files = listOf(mockk()))
            then("숙소가 저장되어야한다.") {
                verify { imageService.uploadImages(any()) }
                verify { accommodationAdapter.save(any()) }

                read.name shouldBe create.name
                read.accommodationId shouldNotBe null
                read.images.size shouldBe 1
                read.images[0] shouldBe "test image url"
                read.features.size shouldBe create.features.size
                read.tags.size shouldBe create.tags.size
                read.location shouldBe create.location

                for (tag in create.tags) tag shouldBeIn read.tags
                for (feature in create.features) feature shouldBeIn read.features
            }
        }
    }

    given("삭제하고 싶은 숙소의 id가 있을 때") {
        val deleteId = "test delete id"

        `when`("숙소 삭제 요청을 하면") {
            every { accommodationAdapter.findByIdOrNull(deleteId) } returns Accommodation(
                name = "test name",
                position = Position(10.0, 20.0),
                location = "test location",
                writer = loginUser,
            )
            every { accommodationAdapter.delete(any()) } returns Unit
            val id = accommodationService.deleteAccommodation(loginId, deleteId)

            then("숙소가 삭제 되어야한다.") {
                id shouldBe deleteId
            }
        }
    }
})