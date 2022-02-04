package com.ams.core.entity

import com.ams.core.common.enum.GenderEnum
import com.ams.core.common.enum.StatusEnum
import com.ams.core.model.StudentModel
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import reactor.core.publisher.Mono

@Table("STUDENTS")
class Student(

    @Id
    val id: Long?,
    var name: String,
    var mobileNumber: String,
    var dateOfBirth: String,
    var gender: GenderEnum,
    var school: String,
    var grade: String,
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
