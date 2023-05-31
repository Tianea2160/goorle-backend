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
    var nickname: String = generatedRandomName(),
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

val names = listOf(
    "자유로한발짝",
    "떠돌이휴가중",
    "떠나자한발짝",
    "발걸음기억해",
    "떠도는휴게소",
    "세계로길잡이",
    "자유의탐험가",
    "떠돌이가이드",
    "도전풍선여행",
    "멋진여정의끝",
)

fun generatedRandomName() = names.random()