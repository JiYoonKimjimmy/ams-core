package com.ems.core.entity

import com.ems.core.common.enum.GenderEnum
import com.ems.core.common.enum.StatusEnum
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("STUDENTS")
data class Students(
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
    val gender: GenderEnum,
    @Column("SCHOOL")
    val school: String,
    @Column("GRADE")
    val grade: String,
    @Column("STATUS")
    val status: StatusEnum
) : BaseEntity()
