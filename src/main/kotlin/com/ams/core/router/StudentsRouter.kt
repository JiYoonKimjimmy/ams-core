package com.ams.core.router

import com.ams.core.entity.Student
import com.ams.core.handler.StudentsHandler
import com.ams.core.model.GetStudentsResponse
import com.ams.core.model.StudentModel
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
class StudentsRouter(
    val studentsHandler: StudentsHandler
) {

    @Bean
    @RouterOperations(
        RouterOperation(
            method = [RequestMethod.GET],
            path = "/api/student/{id}",
            beanClass = StudentsHandler::class,
            beanMethod = "findOne",
            operation = Operation(
                operationId = "getStudent",
                parameters = [Parameter(`in` = ParameterIn.PATH, name = "id")],
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = StudentModel::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.GET],
            path = "/api/students",
            beanClass = StudentsHandler::class,
            beanMethod = "findAll",
            operation = Operation(
                operationId = "getStudents",
                parameters = [
                    Parameter(`in` = ParameterIn.QUERY, name = "number", example = "0"),
                    Parameter(`in` = ParameterIn.QUERY, name = "size", example = "10")
                ],
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = GetStudentsResponse::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.POST],
            path = "/api/student",
            beanClass = StudentsHandler::class,
            beanMethod = "save",
            operation = Operation(
                operationId = "saveStudent",
                requestBody = RequestBody(content = [Content(schema = Schema(implementation = Student::class))]),
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = StudentModel::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.POST],
            path = "/api/student/update",
            beanClass = StudentsHandler::class,
            beanMethod = "update",
            operation = Operation(
                operationId = "updateStudent",
                requestBody = RequestBody(content = [Content(schema = Schema(implementation = Student::class))]),
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = StudentModel::class))])]
            )
        ),
        RouterOperation(
            method = [RequestMethod.DELETE],
            path = "/api/student",
            beanClass = StudentsHandler::class,
            beanMethod = "delete",
            operation = Operation(
                operationId = "deleteStudent",
                parameters = [Parameter(`in` = ParameterIn.QUERY, name = "id")],
                responses = [ApiResponse(responseCode = "200", content = [Content(schema = Schema(implementation = Void::class))])]
            )
        )
    )
    fun studentsRouterFunction() = nest(path("/api"),
        router {
            listOf(
                GET("/student/{id}", studentsHandler::findOne),
                GET("/students", studentsHandler::findAll),
                POST("/student", studentsHandler::save),
                POST("/student/validation", studentsHandler::saveWithValidation),
                POST("/student/update", studentsHandler::update),
                DELETE("/student", studentsHandler::delete),

                filter { serverRequest, function ->
                    println("coRouterFilter : ${serverRequest.path()}")
                    function(serverRequest)
                }
            )
        }
    )

}