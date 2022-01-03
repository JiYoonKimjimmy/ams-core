package com.ams.core.handler

import com.ams.core.model.GetParentsResponse
import com.ams.core.model.PageableModel
import com.ams.core.model.ParentsModel
import com.ams.core.model.StudentModel
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

    fun getOne(request: ServerRequest): Mono<ServerResponse> =
        parentsRepository
            .findById(request.pathVariable("id").toLong())
            .flatMap { ok().body(fromValue(ParentsModel.of(it))) }

    fun getAll(request: ServerRequest): Mono<ServerResponse> =
        parentsRepository
            .findAllBy(PageableModel.toPageRequest(request))
            .map { ParentsModel.of(it) }
            .collectList()
            .zipWith(parentsRepository.count())
            .flatMap { ok().body(fromValue(GetParentsResponse.of(request, it.t1, it.t2))) }

    fun save(request: ServerRequest): Mono<ServerResponse> =
        parentsRepository
            .saveAll(request.bodyToMono(ParentsModel::class.java).map { it.toEntity() })
            .flatMap { ok().body(fromValue(it)) }
            .single()

    fun update(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(ParentsModel::class.java)
            .flatMap { parentsRepository.findById(it.id!!).flatMap { old -> old.updateToMono(it.toEntity()) } }
            .flatMap { parentsRepository.save(it) }
            .flatMap { ok().body(fromValue(it)) }
            .single()

    fun delete(request: ServerRequest): Mono<ServerResponse> =
        parentsRepository
            .deleteById(request.queryParam("id").orElseThrow().toLong())
            .flatMap { ok().build() }

}