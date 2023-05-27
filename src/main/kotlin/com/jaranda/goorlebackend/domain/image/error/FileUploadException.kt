package com.jaranda.goorlebackend.domain.image.error

import com.jaranda.goorlebackend.shared.error.GoorleException

class FileUploadException  : GoorleException(code = "FILE_UPLOAD_ERROR", status = 500)