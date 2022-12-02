package com.ams.core.handler

import com.ams.core.common.base.BaseHandler
import com.ams.core.common.base.BaseResponse
import com.ams.core.common.toPageRequest
import com.ams.core.model.ParentsModel
import com.ams.core.repository.ParentsRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ParentsHandler(
    val parentsRepository: ParentsRepository
) : BaseHandler {

    override fun findOne(request: ServerRequest): Mono<ServerResponse> =
        request
            .pathVariable("id")
            .toLong()
            .let { parentsRepository.findById(it) }
            .flatMap { BaseResponse.ok(body = ParentsModel.of(entity = it)) }

    override fun findAll(request: ServerRequest): Mono<ServerResponse> =
        request
            .toPageRequest()
            .let { parentsRepository.findAllBy(pageable = it).collectList() }
            .zipWith(parentsRepository.count())
            .flatMap { BaseResponse.ok(body = ParentsModel.of(request = request, tuple = it)) }

    override fun save(request: ServerRequest): Mono<ServerResponse> =
        request
            .bodyToMono(ParentsModel::class.java)
            .map { it.toEntity() }
            .flatMap { parentsRepository.save(it) }
            .flatMap { BaseResponse.ok(body = ParentsModel.of(entity = it)) }

    override fun update(request: ServerRequest): Mono<ServerResponse> =
        request
            .bodyToMono(ParentsModel::class.java)
            .zipWhen { parentsRepository.findById(it.id) }
            .flatMap { it.t2.update(model = it.t1) }
            .flatMap { parentsRepository.save(it) }
            .flatMap { BaseResponse.ok(body = ParentsModel.of(entity = it)) }

    override fun delete(request: ServerRequest): Mono<ServerResponse> =
        request
            .queryParam("id")
            .orElseThrow()
            .toLong()
            .let { parentsRepository.deleteById(it) }
            .flatMap { BaseResponse.ok() }

}
