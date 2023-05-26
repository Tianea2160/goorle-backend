package com.jaranda.goorlebackend.domain.`package`.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PackageRepository : JpaRepository<Package, String>