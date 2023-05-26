package com.jaranda.goorlebackend.shared.error.handler

import com.jaranda.goorlebackend.shared.error.GoorleException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalGoorleExceptionHandler {
    @ExceptionHandler(GoorleException::class)
    fun handleGoorleException(e: GoorleException) =
        ResponseEntity.status(e.errorCode.status).body(e.errorCode)
}