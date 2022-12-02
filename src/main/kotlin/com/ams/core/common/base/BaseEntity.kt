package com.ams.core.common.base

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import reactor.core.publisher.Mono
import java.time.LocalDateTime

abstract class BaseEntity<E, M>(
    @CreatedDate
    var created: LocalDateTime? = null,
    @LastModifiedDate
    var updated: LocalDateTime? = null
) {

    abstract fun update(model: M): Mono<E>

}
