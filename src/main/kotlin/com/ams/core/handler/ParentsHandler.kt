package com.ams.core.handler

import com.ams.core.model.PageableModel
import com.ams.core.model.ParentsModel
import com.ams.core.repository.ParentsRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class ParentsHandler(
    val parentsRepository: ParentsRepository
) {

    fun findOne(request: ServerRequest): Mono<ServerResponse> =
        request
            .pathVariable("id")
            .toLong()
            .let { parentsRepository.findById(it) }
//            .flatMap { ok().body(fromValue(ParentsModel().of(it))) }
            .flatMap { ok().body(fromValue(ParentsModel.of(it))) }

    fun findAll(request: ServerRequest): Mono<ServerResponse> =
        PageableModel
            .toPageRequest(request = request)
            .let { parentsRepository.findAllBy(pageable = it).collectList() }
            .zipWith(parentsRepository.count())
//            .flatMap { ok().body(fromValue(ParentsModel().of(request = request, tuple = it))) }
            .flatMap { ok().body(fromValue(ParentsModel.of(request = request, tuple = it))) }

    fun save(request: ServerRequest): Mono<ServerResponse> =
        request
            .bodyToMono(ParentsModel::class.java)
            .map { it.toEntity() }
            .flatMap { parentsRepository.save(it) }
            .flatMap { ok().body(fromValue(it)) }

    fun update(request: ServerRequest): Mono<ServerResponse> =
        request
            .bodyToMono(ParentsModel::class.java)
            .zipWhen { parentsRepository.findById(it.id) }
            .flatMap { it.t2.update(it.t1) }
            .flatMap { parentsRepository.save(it) }
            .flatMap { ok().body(fromValue(it)) }

    fun delete(request: ServerRequest): Mono<ServerResponse> =
        request
            .queryParam("id")
            .orElseThrow()
            .toLong()
            .let { parentsRepository.deleteById(it) }
            .flatMap { ok().build() }

}
