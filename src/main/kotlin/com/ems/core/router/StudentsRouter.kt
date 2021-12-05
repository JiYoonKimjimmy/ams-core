package com.ems.core.router

import com.ems.core.handler.StudentsHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Component
class StudentsRouter(private val handler: StudentsHandler) {

    @Bean
    fun routerFunction() = nest(path("/api"),
        router {
            listOf(
                GET("/student/{id}", handler::getOne),
                GET("/students", handler::getAll),
                POST("/students", handler::save)
            )
        }
    )

}