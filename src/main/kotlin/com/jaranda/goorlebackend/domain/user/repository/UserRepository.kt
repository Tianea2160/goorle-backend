package com.jaranda.goorlebackend.domain.user.repository

import com.jaranda.goorlebackend.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String>