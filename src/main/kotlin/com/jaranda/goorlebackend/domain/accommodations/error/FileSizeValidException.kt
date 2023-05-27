package com.jaranda.goorlebackend.domain.accommodations.error

import com.jaranda.goorlebackend.shared.error.GoorleException

class FileSizeValidException : GoorleException(code = "사진이 없거나, 10개를 초과했습니다.", status = 400)