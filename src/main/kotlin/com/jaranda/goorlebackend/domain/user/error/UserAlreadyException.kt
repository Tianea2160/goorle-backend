package com.jaranda.goorlebackend.domain.user.error

import com.jaranda.goorlebackend.shared.error.GoorleException

class UserAlreadyException : GoorleException(code = "USER_ALREADY_EXISTS", status = 409) {
}
