package com.jaranda.goorlebackend.config

import com.jaranda.goorlebackend.shared.security.filter.JwtFilter
import com.jaranda.goorlebackend.shared.security.handler.JarandaAccessDeniedHandler
import com.jaranda.goorlebackend.shared.security.handler.JarandaAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val jwtFilter: JwtFilter,
    private val accessDeniedHandler: JarandaAccessDeniedHandler,
    private val authenticationEntryPoint: JarandaAuthenticationEntryPoint,
) {
    @Bean
    fun userDetailsService() = UserDetailsService { _ -> null }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .exceptionHandling { config ->
                config.accessDeniedHandler(accessDeniedHandler)
                config.authenticationEntryPoint(authenticationEntryPoint)
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                    .requestMatchers("/api/v1/auth/login").permitAll()
                    .requestMatchers(
                        HttpMethod.GET,
                        "/api/v1/user/me",
                        "/api/v1/accommodations/me",
                        "/api/v1/comments/me"
                    ).authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                    .requestMatchers("/api/v1/**").authenticated()
                    .anyRequest().denyAll()
            }
        return http.build()
    }
}