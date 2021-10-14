package com.ems.core.repository

import com.ems.core.entity.Students
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

interface StudentsRepository: ReactiveCrudRepository<Students, Long>