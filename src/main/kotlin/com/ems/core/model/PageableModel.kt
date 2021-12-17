package com.ems.core.model

import org.springframework.data.domain.PageRequest
import org.springframework.web.reactive.function.server.ServerRequest

class PageableModel {
    companion object {
        fun toPageRequest(request: ServerRequest) =
            PageRequest.of(
                request.queryParam("page").orElse("0").toInt(),
                request.queryParam("pageSize").orElse("10").toInt()
            )
    }
}