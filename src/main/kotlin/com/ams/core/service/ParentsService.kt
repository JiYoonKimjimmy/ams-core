package com.ams.core.service

import com.ams.core.repository.ParentsRepository
import org.springframework.stereotype.Service

@Service
class ParentsService(
    val parentsRepository: ParentsRepository
) {

    fun findAllByStudentId(studentId: Long) = parentsRepository.findAllByStudentId(studentId = studentId)

}