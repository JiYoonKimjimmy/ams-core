package com.ems.core.repository

import com.ems.core.entity.Students
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentsRepository: R2dbcRepository<Students, Long>