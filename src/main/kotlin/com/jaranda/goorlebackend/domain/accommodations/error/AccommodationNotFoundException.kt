package com.jaranda.goorlebackend.domain.accommodations.error

import com.jaranda.goorlebackend.shared.error.GoorleException

class AccommodationNotFoundException : GoorleException(code = "ACCOMMODATION_NOT_FOUND", status = 404)