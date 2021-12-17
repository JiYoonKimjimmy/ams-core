package com.ems.core.repository

import com.ems.core.entity.Students
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveSortingRepository
import reactor.core.publisher.Flux

interface StudentsRepository : ReactiveSortingRepository<Students, Long> {

    fun findAllBy(pageable: Pageable): Flux<Students>

}