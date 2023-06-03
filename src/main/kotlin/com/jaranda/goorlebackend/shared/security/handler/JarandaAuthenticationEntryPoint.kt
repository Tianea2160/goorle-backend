package com.jaranda.goorlebackend.shared.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.jaranda.goorlebackend.shared.error.dto.ErrorCode
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class JarandaAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        val errorCode = ErrorCode("UNAUTHORIZED", 401).let(objectMapper::writeValueAsString)
        response.status = 401
        response.writer.print(errorCode)
        response.contentType = "application/json"
    }
}