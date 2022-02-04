package com.ams.core.repository

import com.ams.core.entity.Teacher
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface TeachersRepository : ReactiveCrudRepository<Teacher, Long> {

    fun findAllBy(pageable: Pageable): Flux<Teacher>

}