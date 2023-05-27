package com.jaranda.goorlebackend.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@Configuration
@EnableRedisRepositories(basePackages = ["com.jaranda.goorlebackend.domain.auth"])
class RedisConfig