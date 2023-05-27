package com.jaranda.goorlebackend.domain.`package`.service

import com.jaranda.goorlebackend.domain.`package`.dto.PackageReadDTO
import com.jaranda.goorlebackend.domain.`package`.repository.PackageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PackageService(
    private val packageRepository: PackageRepository
) {
    @Transactional(readOnly = true)
    fun findAll() = packageRepository.findAll().map { PackageReadDTO.of(it) }
}