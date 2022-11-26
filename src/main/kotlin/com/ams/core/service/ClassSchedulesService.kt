package com.ams.core.service

import com.ams.core.entity.ClassSchedules
import com.ams.core.repository.ClassSchedulesRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ClassSchedulesService(
    val classSchedulesRepository: ClassSchedulesRepository
) {

    fun findAllByClassId(classId: Long): Flux<ClassSchedules> {
        return classSchedulesRepository
            .findAllByClassId(classId = classId)
    }

}