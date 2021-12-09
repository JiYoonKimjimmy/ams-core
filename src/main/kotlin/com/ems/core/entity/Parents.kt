package com.ems.core.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Table("PARENTS")
class Parents(
    @Id
    @Column("ID")
    var id: Long? = null,
    @Column("NAME")
    val name: String,
    @Column("MOBILE_NUMBER")
    val mobileNumber: String,
    @Column("STATUS")
    val status: StatusEnum,

    @JoinColumn(name = "STUDENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    val student: Student?

) : BaseEntity()