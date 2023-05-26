package com.jaranda.goorlebackend.domain.user.error

import com.jaranda.goorlebackend.shared.error.GoorleException

class UserNotFoundException : GoorleException(code = "USER_NOT_FOUND", status = 404)