package com.ems.core.model

import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.web.reactive.function.server.ServerRequest

open class PageableModel<T>(
    open val number: Int,
    open val size: Int,
    open val numberOfElements: Int,
    open val totalPages: Int,
    open val totalElements: Long,
    open val first: Boolean,
    open val last: Boolean,
    open val empty: Boolean,
    open val content: MutableList<T>
) {
    companion object {
        fun toPageRequest(request: ServerRequest) =
            PageRequest.of(
                request.queryParam("number").orElse("0").toInt(),
                request.queryParam("size").orElse("10").toInt()
            )

        fun <T> toPageResponse(request: ServerRequest, content: List<T>, totalSize: Long): PageableModel<T> {
            val pageable = PageImpl(content, toPageRequest(request), totalSize)
            return PageableModel<T>(
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
    }
}