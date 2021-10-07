package com.ems.core.handler

import com.ems.core.repository.StudentsRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import java.util.stream.Collectors.toList

@Component
class StudentsHandler(private val repository: StudentsRepository) {

    fun getAll(request: ServerRequest): Mono<ServerResponse> =
        repository.findAll().collect(toList()).flatMap { ok().body(fromValue(it)) }

}