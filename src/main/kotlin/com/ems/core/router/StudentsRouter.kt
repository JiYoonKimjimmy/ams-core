package com.ems.core.router

import com.ems.core.handler.StudentsHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
class StudentsRouter(private val handler: StudentsHandler) {

    @Bean
    fun routerFunction() = nest(path("/api/students"),
        router {
            listOf(
                GET("/", handler::getAll)
            )
        }
    )

}