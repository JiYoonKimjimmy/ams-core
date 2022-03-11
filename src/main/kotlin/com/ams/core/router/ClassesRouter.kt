package com.ams.core.router

import com.ams.core.handler.ClassesHandler
import com.ams.core.model.ClassesModel
import com.ams.core.model.GetClassesResponse
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
class ClassesRouter(
    val classesHandler: ClassesHandler
) {

    @Bean
    @RouterOperations(
        RouterOperation(
            method = [RequestMethod.GET],
            path = "/api/class/{id}",
            beanClass = ClassesHandler::class,
            beanMethod = "getOne",
            operation = Operation(
                operationId = "getClass",
                parameters = [Parameter(`in` = ParameterIn.PATH, name = "id")],
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ClassesModel::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.GET],
            path = "/api/classes",
            beanClass = ClassesHandler::class,
            beanMethod = "getAll",
            operation = Operation(
                operationId = "getClasses",
                parameters = [
                    Parameter(`in` = ParameterIn.QUERY, name = "number", example = "0"),
                    Parameter(`in` = ParameterIn.QUERY, name = "size", example = "10")
                ],
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = GetClassesResponse::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.POST],
            path = "/api/class",
            beanClass = ClassesHandler::class,
            beanMethod = "save",
            operation = Operation(
                operationId = "saveClass",
                requestBody = RequestBody(content = [Content(schema = Schema(implementation = ClassesModel::class))]),
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ClassesModel::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.POST],
            path = "/api/class/update",
            beanClass = ClassesHandler::class,
            beanMethod = "update",
            operation = Operation(
                operationId = "updateClass",
                requestBody = RequestBody(content = [Content(schema = Schema(implementation = ClassesModel::class))]),
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ClassesModel::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.DELETE],
            path = "/api/class",
            beanClass = ClassesHandler::class,
            beanMethod = "delete",
            operation = Operation(
                operationId = "deleteClass",
                parameters = [Parameter(`in` = ParameterIn.QUERY, name = "id")],
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = Void::class))])]
            )
        )
    )
    fun classesRouterFunction() = nest(path("/api"),
        router {
            listOf(
                GET("/class/{id}", classesHandler::getOne),
                GET("/classes", classesHandler::getAll),
                POST("/class", classesHandler::save),
                POST("/class/update", classesHandler::update),
                DELETE("/class", classesHandler::delete)
            )
        }
    )

}