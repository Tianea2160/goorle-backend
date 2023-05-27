package com.jaranda.goorlebackend.domain.accommodations.error

import com.jaranda.goorlebackend.shared.error.GoorleException

class NoPermissionException : GoorleException(code = "권한이 없습니다.", status = 403)