package com.jaranda.goorlebackend.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {

    @Bean
    fun openapi(): OpenAPI {
        val local = Server()
        local.url = "http://localhost:8866"
        local.description = "로컬 테스팅 용입니다."

        val server = Server()
        local.url = "https://goorle.k-net.kr"
        local.description = "goorle 서비스 용입니다."

        val info = Info()
            .version("v1")
            .title("goorle API")
            .description("goorle backend server 입니다.")

        val jwtSchemeName = "authorization"
        val securityRequirement: SecurityRequirement = SecurityRequirement().addList(jwtSchemeName)
        val components = Components()
            .addSecuritySchemes(
                jwtSchemeName, SecurityScheme()
                    .name(jwtSchemeName)
                    .type(SecurityScheme.Type.HTTP) // HTTP 방식
                    .scheme("bearer")
                    .bearerFormat("JWT")
            )

        return OpenAPI()
            .servers(listOf(local, server))
            .info(info)
            .addSecurityItem(securityRequirement)
            .components(components)
    }
}