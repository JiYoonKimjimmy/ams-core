package com.ams.core.common.base

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

interface BaseHandler {

    fun findOne(request: ServerRequest): Mono<ServerResponse>
    fun findAll(request: ServerRequest): Mono<ServerResponse>
    fun save(request: ServerRequest): Mono<ServerResponse>
    fun update(request: ServerRequest): Mono<ServerResponse>
    fun delete(request: ServerRequest): Mono<ServerResponse>

}