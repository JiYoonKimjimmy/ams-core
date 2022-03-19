package com.ams.core.repository

import com.ams.core.entity.ClassSchedules
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ClassSchedulesRepository : ReactiveCrudRepository<ClassSchedules, Long>