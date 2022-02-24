package com.ams.core.router

import com.ams.core.entity.Teacher
import com.ams.core.handler.TeachersHandler
import com.ams.core.model.GetTeachersResponse
import com.ams.core.model.TeacherModel
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Component
class TeachersRouter(
    val teachersHandler: TeachersHandler
) {

    @Bean
    @RouterOperations(
        RouterOperation(
            method = [RequestMethod.GET],
            path = "/api/teacher/{id}",
            beanClass = TeachersHandler::class,
            beanMethod = "getOne",
            operation = Operation(
                operationId = "getTeacher",
                parameters = [Parameter(`in` = ParameterIn.PATH, name = "id")],
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = TeacherModel::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.GET],
            path = "/api/teachers",
            beanClass = TeachersHandler::class,
            beanMethod = "getAll",
            operation = Operation(
                operationId = "getTeachers",
                parameters = [
                    Parameter(`in` = ParameterIn.QUERY, name = "number", example = "0"),
                    Parameter(`in` = ParameterIn.QUERY, name = "size", example = "10")
                ],
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = GetTeachersResponse::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.POST],
            path = "/api/teacher",
            beanClass = TeachersHandler::class,
            beanMethod = "save",
            operation = Operation(
                operationId = "saveTeacher",
                requestBody = RequestBody(content = [Content(schema = Schema(implementation = Teacher::class))]),
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = TeacherModel::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.PUT],
            path = "/api/teacher",
            beanClass = TeachersHandler::class,
            beanMethod = "update",
            operation = Operation(
                operationId = "updateTeacher",
                requestBody = RequestBody(content = [Content(schema = Schema(implementation = Teacher::class))]),
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = TeacherModel::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.DELETE],
            path = "/api/teacher",
            beanClass = TeachersHandler::class,
            beanMethod = "delete",
            operation = Operation(
                operationId = "deleteTeacher",
                parameters = [Parameter(`in` = ParameterIn.QUERY, name = "id")],
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = Void::class))])]
            )
        )
    )
    fun teachersRouterFunction() = nest(path("/api"),
        router {
            listOf(
                GET("/teacher/{id}", teachersHandler::getOne),
                GET("/teachers", teachersHandler::getAll),
                POST("/teacher", teachersHandler::save),
                PUT("/teacher", teachersHandler::update),
                DELETE("/teacher", teachersHandler::delete)
            )
        }
    )

}