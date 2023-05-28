package com.jaranda.goorlebackend.domain.user.entity

import com.jaranda.goorlebackend.domain.accommodations.entity.Accommodation
import com.jaranda.goorlebackend.domain.comment.entity.Comment
import jakarta.persistence.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
class User(
    @Id
    @Column(name = "user_id")
    val id: String,
    var nickname: String = "초보",
    var score: Long = 0L,
    @OneToMany(mappedBy = "writer", cascade = [CascadeType.ALL])
    val accommodations: List<Accommodation> = mutableListOf(),
    @OneToMany(mappedBy = "writer", cascade = [CascadeType.ALL])
    val comments: List<Comment> = mutableListOf(),
) : UserDetails {
    override fun getAuthorities() = mutableListOf(SimpleGrantedAuthority("ROLE_USER"))

    override fun getPassword() = id
    override fun getUsername() = id
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}