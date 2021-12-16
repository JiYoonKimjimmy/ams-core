package com.ems.core.repository

import com.ems.core.entity.Student
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface StudentsRepository: ReactiveCrudRepository<Student, Long>