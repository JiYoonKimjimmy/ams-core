package com.ams.core.infra.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.r2dbc")
data class R2DBCProperties(
    val url: String,
    val username: String,
    val password: String
) {
    val host: String = url.split(":")[0]
    val path: String = url.split(":")[1]
}
