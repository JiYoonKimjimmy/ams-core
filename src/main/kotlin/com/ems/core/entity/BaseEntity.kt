package com.ems.core.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime

abstract class BaseEntity(
    @CreatedDate
    @Column("CREATED")
    var created: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    @Column("UPDATED")
    var updated: LocalDateTime = LocalDateTime.now()
)