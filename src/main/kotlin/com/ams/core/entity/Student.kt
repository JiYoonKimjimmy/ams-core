package com.ams.core.entity

import com.ams.core.common.enum.GenderEnum
import com.ams.core.common.enum.StatusEnum
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import reactor.core.publisher.Mono

@Table("STUDENTS")
class Student(
    @Id
    @Column("ID")
    var id: Long?,
    @Column("NAME")
    var name: String?,
    @Column("MOBILE_NUMBER")
    var mobileNumber: String?,
    @Column("DATE_OF_BIRTH")
    var dateOfBirth: String?,
    @Column("GENDER")
    var gender: GenderEnum?,
    @Column("SCHOOL")
    var school: String?,
    @Column("GRADE")
    var grade: String?,
    @Column("STATUS")
    var status: StatusEnum? = StatusEnum.ACTIVE
) : BaseEntity() {
    @Transient
    val parents: MutableList<Parents> = mutableListOf()

    fun updateToMono(request: Student): Mono<Student> {
        this.name = request.name ?: this.name
        this.mobileNumber = request.mobileNumber ?: this.mobileNumber
        this.dateOfBirth = request.dateOfBirth ?: this.dateOfBirth
        this.gender = request.gender ?: this.gender
        this.school = request.school ?: this.school
        this.grade = request.grade ?: this.grade
        this.status = request.status ?: this.status
        return Mono.just(this)
    }
}