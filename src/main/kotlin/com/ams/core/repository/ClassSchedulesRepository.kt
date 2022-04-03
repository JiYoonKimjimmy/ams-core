package com.ams.core.repository

import com.ams.core.entity.ClassSchedules
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ClassSchedulesRepository : ReactiveCrudRepository<ClassSchedules, Long> {

    fun findAllByClassId(classId: Long): Flux<ClassSchedules>

}