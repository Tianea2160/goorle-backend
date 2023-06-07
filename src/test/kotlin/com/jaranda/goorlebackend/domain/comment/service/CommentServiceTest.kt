package com.jaranda.goorlebackend.domain.comment.service

import com.jaranda.goorlebackend.domain.comment.dto.CommentCreateDTO
import com.jaranda.goorlebackend.domain.comment.entity.Comment
import com.jaranda.goorlebackend.domain.user.entity.User
import com.jaranda.goorlebackend.infra.accommodations.adapter.AccommodationAdapter
import com.jaranda.goorlebackend.infra.comment.adapter.CommentAdapter
import com.jaranda.goorlebackend.infra.user.adapter.UserAdapter
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class CommentServiceTest : BehaviorSpec({
    val commentAdapter: CommentAdapter = mockk()
    val userAdapter: UserAdapter = mockk()
    val accommodationAdapter: AccommodationAdapter = mockk()
    val commentService = CommentService(commentAdapter, userAdapter, accommodationAdapter)
    val loginId = "test login id"
    every { userAdapter.findByIdOrNull(loginId) } returns User(id = "test user id")

    given("댓글 작성 정보가 있을 때") {
        val create = CommentCreateDTO(
            content = "hello world",
        )
        val accommodationId = "test accommodation id"
        `when`("댓글 생성 요청을 하면") {
            every { accommodationAdapter.findByIdOrNull(accommodationId) } returns mockk()
            every { commentAdapter.save(any()) } returnsArgument 0

            val commendId = commentService.createComment(loginId, accommodationId, create)

            then("댓글이 생성되어야한다.") {
                commendId shouldNotBe null
            }
        }

        val deleteCommentId = "test delete comment id"
        val targetAccommodationId = "test target accommodation id"
        `when`("댓글 삭제 요청을 하면") {
            every { commentAdapter.findByIdOrNull(deleteCommentId) } returns Comment(
                writer = User(id = loginId),
                accommodation = mockk(),
                content = "test content",
            )
            every { commentAdapter.deleteById(deleteCommentId) } returns Unit
            val id = commentService.deleteComment(loginId, targetAccommodationId, deleteCommentId)
            then("댓글이 삭제되어야한다.") {
                id shouldBe deleteCommentId
                verify { commentAdapter.deleteById(deleteCommentId) }
            }
        }
    }
})