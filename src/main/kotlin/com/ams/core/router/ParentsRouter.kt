package com.ams.core.router

import com.ams.core.entity.Parents
import com.ams.core.handler.ParentsHandler
import com.ams.core.model.GetParentsResponse
import com.ams.core.model.ParentsModel
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
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.router

@Component
class ParentsRouter(
    val parentsHandler: ParentsHandler
) {

    @Bean
    @RouterOperations(
        RouterOperation(
            method = [RequestMethod.GET],
            path = "/api/parents/{id}",
            beanClass = ParentsHandler::class,
            beanMethod = "findOne",
            operation = Operation(
                operationId = "findParents",
                parameters = [Parameter(`in` = ParameterIn.PATH, name = "id")],
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ParentsModel::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.GET],
            path = "/api/parents",
            beanClass = ParentsHandler::class,
            beanMethod = "findAll",
            operation = Operation(
                operationId = "findParents",
                parameters = [
                    Parameter(`in` = ParameterIn.QUERY, name = "number", example = "0"),
                    Parameter(`in` = ParameterIn.QUERY, name = "size", example = "10")
                ],
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = GetParentsResponse::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.POST],
            path = "/api/parents",
            beanClass = ParentsHandler::class,
            beanMethod = "save",
            operation = Operation(
                operationId = "saveParents",
                requestBody = RequestBody(content = [Content(schema = Schema(implementation = Parents::class))]),
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ParentsModel::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.POST],
            path = "/api/parents/update",
            beanClass = ParentsHandler::class,
            beanMethod = "update",
            operation = Operation(
                operationId = "updateParents",
                requestBody = RequestBody(content = [Content(schema = Schema(implementation = Parents::class))]),
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ParentsModel::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.DELETE],
            path = "/api/parents",
            beanClass = ParentsHandler::class,
            beanMethod = "delete",
            operation = Operation(
                operationId = "deleteParents",
                parameters = [Parameter(`in` = ParameterIn.QUERY, name = "id")],
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = Void::class))])]
            )
        )
    )
    fun parentsRouterFunction() = RouterFunctions.nest(RequestPredicates.path("/api"),
        router {
            listOf(
                GET("/parents/{id}", parentsHandler::findOne),
                GET("/parents", parentsHandler::findAll),
                POST("/parents", parentsHandler::save),
                POST("/parents/update", parentsHandler::update),
                DELETE("/parents", parentsHandler::delete)
            )
        }
    )

}