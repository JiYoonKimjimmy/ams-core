package com.ams.core.repository

import com.ams.core.entity.Parents
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ParentsRepository : ReactiveCrudRepository<Parents, Long> {

    fun findAllBy(pageable: Pageable): Flux<Parents>

}