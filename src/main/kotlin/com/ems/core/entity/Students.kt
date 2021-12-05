package com.ems.core.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("STUDENTS")
class Students(
    @Id
    @Column("ID")
    var id: Long? = null,
    @Column("NAME")
    val name: String,
    @Column("MOBILE_NUMBER")
    val mobileNumber: String,
    @Column("DATE_OF_BIRTH")
    val dateOfBirth: String,
    @Column("GENDER")
    val gender: String,
    @Column("SCHOOL")
    val school: String,
    @Column("GRADE")
    val grade: String,
    @Column("STATUS")
    val status: StatusEnum
) : BaseEntity()

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
