package com.jaranda.goorlebackend.domain.image.error

import com.jaranda.goorlebackend.shared.error.GoorleException

class FileDeleteException : GoorleException(code = "FILE_DELETE_ERROR", status = 500)