package com.ams.core.handler

import com.ams.core.common.toPageRequest
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
        request
            .pathVariable("id")
            .toLong()
            .let { teachersRepository.findById(it) }
            .flatMap { ok().body(fromValue(TeacherModel.of(entity = it))) }

    fun findAll(request: ServerRequest): Mono<ServerResponse> =
        request
            .toPageRequest()
            .let { teachersRepository.findAllBy(pageable = it).collectList() }
            .zipWith(teachersRepository.count())
            .flatMap { ok().body(fromValue(TeacherModel.of(request = request, tuple = it))) }

    fun save(request: ServerRequest): Mono<ServerResponse> =
        request
            .bodyToMono(TeacherModel::class.java)
            .map { it.toEntity() }
            .flatMap { teachersRepository.save(it) }
            .flatMap { ok().body(fromValue(TeacherModel.of(entity = it))) }
            .single()

    fun update(request: ServerRequest): Mono<ServerResponse> =
        request
            .bodyToMono(TeacherModel::class.java)
            .zipWhen { teachersRepository.findById(it.id) }
            .flatMap { it.t2.update(model = it.t1) }
            .flatMap { teachersRepository.save(it) }
            .flatMap { ok().body(fromValue(TeacherModel.of(entity = it))) }

    fun delete(request: ServerRequest): Mono<ServerResponse> =
        request
            .queryParam("id")
            .orElseThrow()
            .toLong()
            .let { teachersRepository.deleteById(it) }
            .flatMap { ok().build() }

}
