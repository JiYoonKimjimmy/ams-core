package com.ams.core.repository

import com.ams.core.entity.Classes
import com.ams.core.entity.Parents
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ClassesRepository : ReactiveCrudRepository<Classes, Long> {

    fun findAllBy(pageable: Pageable): Flux<Classes>

    fun findAllByTeacherId(teacherId: Long): Flux<Classes>

}