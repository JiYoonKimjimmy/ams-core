package com.ams.core.common.base

import org.springframework.web.reactive.function.server.ServerRequest
import reactor.util.function.Tuple2

abstract class BaseModel<E, M> {

    abstract fun of(entity: E): M

    fun of(request: ServerRequest, tuple: Tuple2<List<E>, Long>): PageableModel<M> {
        return PageableModel.toResponse(request = request, tuple = tuple, convertFunction = this::of)
    }

}