package com.ams.core.repository

import com.ams.core.entity.Student
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface StudentsRepository : ReactiveCrudRepository<Student, Long> {

    fun findAllBy(pageable: Pageable): Flux<Student>

}