package com.jaranda.goorlebackend.web.accommodation.controller

import com.jaranda.goorlebackend.domain.accommodations.dto.AccommodationCreateDTO
import com.jaranda.goorlebackend.domain.accommodations.service.AccommodationService
import com.jaranda.goorlebackend.domain.comment.dto.CommentCreateDTO
import com.jaranda.goorlebackend.domain.comment.service.CommentService
import com.jaranda.goorlebackend.shared.error.dto.ErrorCode
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/accommodations")
class AccommodationController(
    private val accommodationService: AccommodationService,
    private val commentService: CommentService,
) {
    @GetMapping("")
    @Operation(tags = ["숙소"], summary = "숙소 전체 목록 조회")
    fun findAll() = accommodationService.findAll()

    @GetMapping("/user/{userId}")
    @Operation(tags = ["숙소"], summary = "사용자 id로 특정 사용자의 숙소 목록 조회")
    fun findAllByUser(
        @PathVariable userId: String
    ) = accommodationService.findAllByUserId(userId)

    @GetMapping("/me")
    @Operation(tags = ["숙소"], summary = "내가 작성한 숙소 목록 조회")
    fun findMyAccommodation(
        authentication: Authentication
    ) = accommodationService.findAllByUserId(authentication.name)

    @GetMapping("/{accommodationId}")
    @Operation(tags = ["숙소"], summary = "숙소 id로 숙소 조회")
    fun findById(@PathVariable accommodationId: String) = accommodationService.findById(accommodationId)

    @PostMapping("", consumes = [MULTIPART_FORM_DATA_VALUE])
    @Operation(tags = ["숙소"], summary = "숙소 생성")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "숙소 생성 성공"),
            ApiResponse(
                responseCode = "400",
                description = "이미지가 없거나 10개를 초과한 경우",
                content = [Content(schema = Schema(implementation = ErrorCode::class))]
            ),
            ApiResponse(
                responseCode = "500",
                description = "이미지 업로드 중 에러가 발생한 경우",
                content = [Content(schema = Schema(implementation = ErrorCode::class))]
            ),

        ]
    )
    fun createAccommodation(
        authentication: Authentication,
        @Parameter(
            content = [Content(mediaType = APPLICATION_JSON_VALUE)],
            description = "숙소 정보는 application/json 형식으로 주세요. key는 'data'로 주세요."
        )
        @RequestPart("data") create: AccommodationCreateDTO,
        @RequestPart("images")
        @Parameter(
            content = [Content(mediaType = MULTIPART_FORM_DATA_VALUE)],
            description = "이미지는 multipart/form-data 형식으로 주세요. key는 'images'로 주세요."
        )
        files: List<MultipartFile>,
    ) = accommodationService.createAccommodation(authentication.name, create, files)

    @DeleteMapping("/{accommodationId}")
    @Operation(tags = ["숙소"], summary = "숙소 삭제")
    fun deleteAccommodation(
        authentication: Authentication,
        @PathVariable("accommodationId") accommodationId: String
    ) = accommodationService.deleteAccommodation(authentication.name, accommodationId)

    @PostMapping("/{accommodationId}/comments")
    @Operation(tags = ["댓글"], summary = "댓글 조회")
    fun createComment(
        authentication: Authentication,
        @PathVariable("accommodationId") accommodationId: String,
        @RequestBody create: CommentCreateDTO,
    ) = commentService.createComment(authentication.name, accommodationId, create)

    @DeleteMapping("/{accommodationId}/comments/{commentId}")
    @Operation(tags = ["댓글"], summary = "댓글 삭제")
    fun deleteComment(
        authentication: Authentication,
        @PathVariable("accommodationId") accommodationId: String,
        @PathVariable("commentId") commentId: String,
    ) = commentService.deleteComment(authentication.name, accommodationId, commentId)
}
