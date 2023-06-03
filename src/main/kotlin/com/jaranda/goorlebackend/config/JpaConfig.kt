package com.jaranda.goorlebackend.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.jaranda.goorlebackend.infra"])
class JpaConfig