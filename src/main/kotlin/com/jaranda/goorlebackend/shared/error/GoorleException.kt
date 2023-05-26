package com.jaranda.goorlebackend.shared.error

import com.jaranda.goorlebackend.shared.error.dto.ErrorCode

open class GoorleException(
    code: String,
    status: Int
) : RuntimeException() {
    val errorCode = ErrorCode(code = code, status = status)
}

