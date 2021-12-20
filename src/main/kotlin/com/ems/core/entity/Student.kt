package com.ems.core.entity

import com.ems.core.common.enum.GenderEnum
import com.ems.core.common.enum.StatusEnum
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import reactor.core.publisher.Mono

@Table("STUDENTS")
data class Student(
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
