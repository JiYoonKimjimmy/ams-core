package com.ems.core.repository

import com.ems.core.entity.Students
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface StudentsRepository : ReactiveCrudRepository<Students, Long> {

    fun findAllBy(pageable: Pageable): Flux<Students>

}