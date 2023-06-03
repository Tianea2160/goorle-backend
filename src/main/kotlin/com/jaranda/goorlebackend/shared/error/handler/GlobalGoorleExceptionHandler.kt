package com.jaranda.goorlebackend.shared.error.handler

import com.jaranda.goorlebackend.shared.error.GoorleException
import com.jaranda.goorlebackend.shared.error.dto.ErrorCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.support.MissingServletRequestPartException

@RestControllerAdvice
class GlobalGoorleExceptionHandler {
    @ExceptionHandler(GoorleException::class)
    fun handleGoorleException(e: GoorleException) =
        ResponseEntity.status(e.errorCode.status).body(e.errorCode)

    @ExceptionHandler(MissingServletRequestPartException::class)
    fun handlerException(e: MissingServletRequestPartException) =
        ResponseEntity.status(400).body(ErrorCode(code = "missing request field or part", status = 400))

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handlerException(e : HttpMessageNotReadableException) =
        ResponseEntity.status(400).body(ErrorCode(code = "bad request, check that json field is correct", status = 400))

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception) =
        ResponseEntity.status(500).body(ErrorCode(code = "internal server error", status = 500))
}