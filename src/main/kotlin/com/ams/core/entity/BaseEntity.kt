package com.ams.core.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDateTime

abstract class BaseEntity(
    @CreatedDate
    @Column("CREATED")
    var created: LocalDateTime? = null,

    @LastModifiedDate
    @Column("UPDATED")
    var updated: LocalDateTime? = null
)