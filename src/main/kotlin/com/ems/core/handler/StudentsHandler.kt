package com.ems.core.handler

import com.ems.core.entity.Students
import com.ems.core.model.GetStudentsResponse
import com.ems.core.model.PageableModel
import com.ems.core.repository.StudentsRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class StudentsHandler(
    val studentsRepository: StudentsRepository
) {

    fun getOne(request: ServerRequest): Mono<ServerResponse> =
        studentsRepository
            .findById(request.pathVariable("id").toLong())
            .flatMap { ok().body(fromValue(it)) }

    fun getAll(request: ServerRequest): Mono<ServerResponse> =
        studentsRepository
            .findAllBy(PageableModel.toPageRequest(request))
            .collectList()
            .flatMap { ok().body(fromValue(GetStudentsResponse(it))) }

    fun save(request: ServerRequest): Mono<ServerResponse> =
        studentsRepository
            .saveAll(request.bodyToMono(Students::class.java))
            .flatMap { ok().body(fromValue(it)) }
            .single()

}