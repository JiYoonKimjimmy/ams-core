package com.ams.core.common.base

import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

data class BaseResponse(
    val result: ResponseCode? = ResponseCode.SUCCESS,
    val data: Any? = null
) {

    companion object {
        fun ok(body: Any? = null): Mono<ServerResponse> = BaseResponse(data = body).response()
    }

    fun response(): Mono<ServerResponse> = ServerResponse.ok().body(fromValue(BaseResponse(result = result, data = data)))

}