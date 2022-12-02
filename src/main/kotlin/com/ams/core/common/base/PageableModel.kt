package com.ams.core.common.base

import com.ams.core.common.toPageRequest
import org.springframework.data.domain.PageImpl
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.util.function.Tuple2

open class PageableModel<T>(
    open val number: Int = 0,
    open val size: Int = 0,
    open val numberOfElements: Int = 0,
    open val totalPages: Int = 0,
    open val totalElements: Long = 0,
    open val first: Boolean = false,
    open val last: Boolean = false,
    open val empty: Boolean = false,
    open val content: List<T> = listOf()
) {
    companion object {
        fun <E, M> toResponse(request: ServerRequest, tuple: Tuple2<List<E>, Long>, convertFunction: (E) -> M): PageableModel<M> =
            tuple
                .t1
                .map(convertFunction)
                .let { PageImpl(it, request.toPageRequest(), tuple.t2) }
                .let { PageableModel(pageable = it) }
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
