package com.ams.core.model

import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.web.reactive.function.server.ServerRequest

data class PageableModel<T>(
    val number: Int,
    val size: Int,
    val numberOfElements: Int,
    val totalPages: Int,
    val totalElements: Long,
    val first: Boolean,
    val last: Boolean,
    val empty: Boolean,
    val content: MutableList<T>
) {
    companion object {
        fun toPageRequest(request: ServerRequest) =
            PageRequest.of(
                request.queryParam("number").orElse("0").toInt(),
                request.queryParam("size").orElse("10").toInt()
            )

    }

    constructor(pageable: PageImpl<T>) : this(
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