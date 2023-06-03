package com.jaranda.goorlebackend.infra.user.repository

import com.jaranda.goorlebackend.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String>{
    @Query("SELECT u FROM User u ORDER BY u.score DESC LIMIT 6")
    fun findGoorlePickers(): List<User>
}