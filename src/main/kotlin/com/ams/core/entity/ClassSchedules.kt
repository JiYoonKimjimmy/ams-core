package com.ams.core.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("CLASS_SCHEDULES")
data class ClassSchedules(

    @Id
    val id: Long? = null,
    val type: String,
    val year: String,
    val month: String,
    val day: String,
    val status: String,
    val classId: Long

) : BaseEntity()