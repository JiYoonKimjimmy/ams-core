package com.ems.core.repository

import com.ems.core.entity.Student
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface StudentsRepository: ReactiveCrudRepository<Student, Long>