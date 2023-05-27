package com.jaranda.goorlebackend.domain.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
class User(
    @Id
    @Column(name = "user_id")
    val id: String,
    val nickname: String = "초보",
    val score: Long = 0L,
) : UserDetails {
    override fun getAuthorities() = mutableListOf(SimpleGrantedAuthority("ROLE_USER"))

    override fun getPassword() = id
    override fun getUsername() = id
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}