package com.jaranda.goorlebackend.domain.`package`.repository

import com.jaranda.goorlebackend.domain.`package`.entity.Package
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PackageRepository : JpaRepository<Package, String>