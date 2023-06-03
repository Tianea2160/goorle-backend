package com.jaranda.goorlebackend.shared.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.jaranda.goorlebackend.shared.error.dto.ErrorCode
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class JarandaAccessDeniedHandler(
    private val objectMapper: ObjectMapper
) : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        val errorCode = ErrorCode("ACCESS_DENIED", 403)
            .let(objectMapper::writeValueAsString)
        response.writer.print(errorCode)
        response.status = 403
        response.contentType = "application/json"
    }
}