package com.ams.core.entity

import com.ams.core.common.enum.GenderEnum
import com.ams.core.common.enum.StatusEnum
import com.ams.core.model.StudentModel
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import reactor.core.publisher.Mono

@Table("STUDENTS")
class Student(
    @Id
    @Column("ID")
    val id: Long?,
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
    var status: StatusEnum = StatusEnum.ACTIVE
) : BaseEntity() {

    fun update(request: StudentModel) = Mono.just(
        this.apply {
            name = request.name ?: name
            mobileNumber = request.mobileNumber ?: mobileNumber
            dateOfBirth = request.dateOfBirth ?: dateOfBirth
            gender = request.gender ?: gender
            school = request.school ?: school
            grade = request.grade ?: grade
            status = request.status ?: status
        })

}
