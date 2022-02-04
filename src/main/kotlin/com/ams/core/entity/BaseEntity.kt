package com.ams.core.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

abstract class BaseEntity(

    @CreatedDate
    var created: LocalDateTime? = null,
    @LastModifiedDate
    var updated: LocalDateTime? = null

)