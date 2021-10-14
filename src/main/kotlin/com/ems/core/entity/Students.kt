package com.ems.core.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("STUDENTS")
data class Students(
    @Id
    var id: Long? = null,
    var name: String? = null,
    var mobileNumber: String? = null,
    var dateOfBirth: String? = null,
    var gender: String? = null,
    var school: String? = null,
    var grade: String? = null,
    var status: StatusEnum = StatusEnum.ACTIVE,
    var version: Int = 0,
    var created: LocalDate = LocalDate.now(),
    var updated: LocalDate = LocalDate.now()
)

enum class GenderEnum(val value: String) {
    Male("M"),
    Female("F");

    companion object {
        fun find(value: String): GenderEnum = values().find { it.value == value }!!
    }
}

enum class StatusEnum {
    ACTIVE,
    DELETED
}
