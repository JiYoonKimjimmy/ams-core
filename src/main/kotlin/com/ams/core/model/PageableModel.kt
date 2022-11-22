package com.ams.core.model

import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.util.function.Tuple2

open class PageableModel<T>(
    open val number: Int,
    open val size: Int,
    open val numberOfElements: Int,
    open val totalPages: Int,
    open val totalElements: Long,
    open val first: Boolean,
    open val last: Boolean,
    open val empty: Boolean,
    open val content: List<T>
) {
    companion object {
        fun toPageRequest(request: ServerRequest) =
            PageRequest.of(
                request.queryParam("number").orElse("0").toInt(),
                request.queryParam("size").orElse("10").toInt()
            )

        fun <E, M> toResponse(request: ServerRequest, tuple: Tuple2<List<E>, Long>, convertFunction: (E) -> M): PageableModel<M> {
            return tuple
                .t1
                .map(convertFunction)
                .let { PageImpl(it, toPageRequest(request = request), tuple.t2) }
                .let { PageableModel(pageable = it) }
        }

    }

    constructor(pageable: PageImpl<T>): this(
        number = pageable.number,
        size = pageable.size,
        numberOfElements = pageable.numberOfElements,
        totalPages = pageable.totalPages,
        totalElements = pageable.totalElements,
        first = pageable.isFirst,
        last = pageable.isLast,
        empty = pageable.isEmpty,
        content = pageable.content
    )

}