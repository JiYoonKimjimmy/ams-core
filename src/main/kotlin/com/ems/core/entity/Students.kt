package com.ems.core.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("STUDENTS")
data class Students(
    @Id
    @Column("ID")
    var id: Long? = null,
    @Column("NAME")
    var name: String? = null,
    @Column("MOBILE_NUMBER")
    var mobileNumber: String? = null,
    @Column("DATE_OF_BIRTH")
    var dateOfBirth: String? = null,
    @Column("GENDER")
    var gender: String? = null,
    @Column("SCHOOL")
    var school: String? = null,
    @Column("GRADE")
    var grade: String? = null,
    @Column("VERSION")
    var version: Int = 0,
    @Column("CREATED")
    var created: LocalDate = LocalDate.now(),
    @Column("UPDATED")
    var updated: LocalDate = LocalDate.now()
)