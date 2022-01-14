package com.ams.core.model

import com.ams.core.common.enum.GenderEnum
import com.ams.core.common.enum.StatusEnum
import com.ams.core.entity.Student
import org.springframework.data.domain.PageImpl
import org.springframework.web.reactive.function.server.ServerRequest

data class StudentModel(
    val id: Long,
    val name: String?,
    val mobileNumber: String?,
    val dateOfBirth: String?,
    val gender: GenderEnum?,
    val school: String?,
    val grade: String?,
    val status: StatusEnum?
) {
    companion object {
        fun of(student: Student) = StudentModel(
            id = student.id!!,
            name = student.name,
            mobileNumber = student.mobileNumber,
            dateOfBirth = student.dateOfBirth,
            gender = student.gender,
            school = student.school,
            grade = student.grade,
            status = student.status
        )

        fun of(request: ServerRequest, content: List<StudentModel>, totalSize: Long) =
            PageableModel(PageImpl(content, PageableModel.toPageRequest(request), totalSize))

    }

    fun toEntity() = Student(
        id = id,
        name = name!!,
        mobileNumber = mobileNumber!!,
        dateOfBirth = dateOfBirth!!,
        gender = gender!!,
        school = school!!,
        grade = grade!!
    )
}