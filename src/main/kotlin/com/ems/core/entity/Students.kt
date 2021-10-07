package com.ems.core.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "students")
class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var name: String? = null
    var mobileNumber: String? = null
    var dateOfBirth: String? = null
    var gender: String? = null
    var school: String? = null
    var grade: String? = null
    var version: Int = 0
    var created: LocalDate = LocalDate.now()
    var updated: LocalDate = LocalDate.now()
}