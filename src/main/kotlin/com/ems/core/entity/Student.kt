package com.ems.core.entity

import com.ems.core.common.enum.GenderEnum
import com.ems.core.common.enum.StatusEnum
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("STUDENTS")
data class Student(
    @Id
    @Column("ID")
    var id: Long? = null,
    @Column("NAME")
    var name: String,
    @Column("MOBILE_NUMBER")
    var mobileNumber: String,
    @Column("DATE_OF_BIRTH")
    var dateOfBirth: String,
    @Column("GENDER")
    var gender: GenderEnum,
    @Column("SCHOOL")
    var school: String,
    @Column("GRADE")
    var grade: String,
    @Column("STATUS")
    var status: StatusEnum
) : BaseEntity()
