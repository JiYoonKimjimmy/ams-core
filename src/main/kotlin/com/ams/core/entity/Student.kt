package com.ams.core.entity

import com.ams.core.common.base.BaseEntity
import com.ams.core.common.enumerate.GenderEnum
import com.ams.core.common.enumerate.StatusEnum
import com.ams.core.model.StudentModel
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import reactor.core.publisher.Mono

@Table("STUDENTS")
data class Student(

    @Id
    val id: Long? = null,
    var name: String,
    var mobileNumber: String,
    var dateOfBirth: String,
    var gender: GenderEnum,
    var school: String,
    var grade: String,
    var status: StatusEnum = StatusEnum.ACTIVE

) : BaseEntity<Student, StudentModel>() {

    override fun update(model: StudentModel) =
        this.apply {
            name = model.name ?: name
            mobileNumber = model.mobileNumber ?: mobileNumber
            dateOfBirth = model.dateOfBirth ?: dateOfBirth
            gender = model.gender ?: gender
            school = model.school ?: school
            grade = model.grade ?: grade
            status = model.status ?: status
        }.let { Mono.just(it) }

}
