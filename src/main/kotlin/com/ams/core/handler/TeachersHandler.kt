package com.ams.core.handler

import com.ams.core.model.PageableModel
import com.ams.core.model.TeacherModel
import com.ams.core.repository.TeachersRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class TeachersHandler(
    val teachersRepository: TeachersRepository
) {

    fun findOne(request: ServerRequest): Mono<ServerResponse> =
        teachersRepository
            .findById(request.pathVariable("id").toLong())
            .flatMap { ok().body(fromValue(TeacherModel.of(it))) }

    fun findAll(request: ServerRequest): Mono<ServerResponse> =
        teachersRepository
            .findAllBy(PageableModel.toPageRequest(request))
            .map { TeacherModel.of(it) }
            .collectList()
            .zipWith(teachersRepository.count())
            .flatMap { ok().body(fromValue(TeacherModel.of(request, it.t1, it.t2))) }

    fun save(request: ServerRequest): Mono<ServerResponse> =
        teachersRepository
            .saveAll(request.bodyToMono(TeacherModel::class.java).map { it.toEntity() })
            .flatMap { ok().body(fromValue(it)) }
            .single()

    fun update(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(TeacherModel::class.java)
            .flatMap { teachersRepository.findById(it.id).flatMap { old -> old.update(it) } }
            .flatMap { teachersRepository.save(it) }
            .flatMap { ok().body(fromValue(it)) }
            .single()

    fun delete(request: ServerRequest): Mono<ServerResponse> =
        teachersRepository
            .deleteById(request.queryParam("id").orElseThrow().toLong())
            .flatMap { ok().build() }

}