package com.ams.core.entity

import com.ams.core.common.enum.GenderEnum
import com.ams.core.common.enum.StatusEnum
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("PARENTS")
data class Parents(
    @Id
    @Column("ID")
    var id: Long? = null,
    @Column("NAME")
    val name: String,
    @Column("MOBILE_NUMBER")
    val mobileNumber: String,
    @Column("GENDER")
    val gender: GenderEnum,
    @Column("STATUS")
    val status: StatusEnum,
    @Column("STUDENT_ID")
    val studentId: Long
) : BaseEntity()