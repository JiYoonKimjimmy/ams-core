package com.ems.core.students

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "STUDENTS")
class Students(
    @Id
    var id: Long,
    var name: String,
    var mobileNumber: String,
    var dateOfBirth: String,
    var gender: String,
    var school: String,
    var grade: String,
    var version: Int,
    var created: Date,
    var updated: Date
)