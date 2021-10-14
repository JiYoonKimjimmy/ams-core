package com.ems.core.handler

import com.ems.core.entity.Students
import com.ems.core.repository.StudentsRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.created
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import java.net.URI
import java.util.stream.Collectors.toList

@Component
class StudentsHandler(private val repository: StudentsRepository) {

    fun getOne(request: ServerRequest): Mono<ServerResponse> =
        repository.findById(request.bodyToMono<Students>().mapNotNull { it.id }).flatMap { ok().body(fromValue(it)) }

    fun getAll(request: ServerRequest): Mono<ServerResponse> =
        repository.findAll().collect(toList()).flatMap { ok().body(fromValue(it)) }

    fun save(request: ServerRequest): Mono<ServerResponse> =
        repository.saveAll(request.bodyToMono<Students>()).flatMap { created(URI.create("/students")).build() }.next()

}