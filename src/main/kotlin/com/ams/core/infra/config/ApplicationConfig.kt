package com.ams.core.infra.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing

@EnableR2dbcAuditing
@OpenAPIDefinition(
    info = Info(
        title = "AMS Swagger",
        version = "1.0",
        description = "Academy Management Service"
    )
)
@Configuration
class ApplicationConfig