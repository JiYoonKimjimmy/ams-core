package com.ams.core.router

import com.ams.core.entity.ClassSchedules
import com.ams.core.handler.ClassSchedulesHandler
import com.ams.core.model.ClassSchedulesModel
import com.ams.core.model.GetClassSchedulesResponse
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
class ClassSchedulesRouter(
    val classSchedulesHandler: ClassSchedulesHandler
) {

    @Bean
    @RouterOperations(
        RouterOperation(
            method = [RequestMethod.GET],
            path = "/api/classSchedule/{id}",
            beanClass = ClassSchedulesHandler::class,
            beanMethod = "findOne",
            operation = Operation(
                operationId = "findClassSchedule",
                parameters = [Parameter(`in` = ParameterIn.PATH, name = "id")],
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ClassSchedulesModel::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.GET],
            path = "/api/classSchedules",
            beanClass = ClassSchedulesHandler::class,
            beanMethod = "findAll",
            operation = Operation(
                operationId = "findClassSchedules",
                parameters = [
                    Parameter(`in` = ParameterIn.QUERY, name = "number", example = "0"),
                    Parameter(`in` = ParameterIn.QUERY, name = "size", example = "10")
                ],
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = GetClassSchedulesResponse::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.POST],
            path = "/api/classSchedule",
            beanClass = ClassSchedulesHandler::class,
            beanMethod = "save",
            operation = Operation(
                operationId = "saveClassSchedule",
                requestBody = RequestBody(content = [Content(schema = Schema(implementation = ClassSchedules::class))]),
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ClassSchedulesModel::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.POST],
            path = "/api/classSchedule/update",
            beanClass = ClassSchedulesHandler::class,
            beanMethod = "update",
            operation = Operation(
                operationId = "updateClassSchedule",
                requestBody = RequestBody(content = [Content(schema = Schema(implementation = ClassSchedules::class))]),
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = ClassSchedulesModel::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.DELETE],
            path = "/api/classSchedule",
            beanClass = ClassSchedulesHandler::class,
            beanMethod = "delete",
            operation = Operation(
                operationId = "deleteClassSchedule",
                parameters = [Parameter(`in` = ParameterIn.QUERY, name = "id")],
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = Void::class))])]
            )
        )
    )
    fun classSchedulesRouterFunction() = nest(path("/api"),
        router {
            listOf(
                GET("/classSchedule/{id}", classSchedulesHandler::findOne),
                GET("/classSchedules", classSchedulesHandler::findAll),
                POST("/classSchedule", classSchedulesHandler::save),
                POST("/classSchedule/update", classSchedulesHandler::update),
                DELETE("/classSchedule", classSchedulesHandler::delete)
            )
        }
    )

}
