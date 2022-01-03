package com.ams.core.model

import com.ams.core.common.enum.GenderEnum
import com.ams.core.common.enum.StatusEnum
import com.ams.core.entity.Student
import org.springframework.data.domain.PageImpl
import org.springframework.web.reactive.function.server.ServerRequest

data class StudentModel(
    val id: Long?,
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
            id = student.id,
            name = student.name,
            mobileNumber = student.mobileNumber,
            dateOfBirth = student.dateOfBirth,
            gender = student.gender,
            school = student.school,
            grade = student.grade,
            status = student.status
        )
    }

    fun toEntity() = Student(
        id = id,
        name = name,
        mobileNumber = mobileNumber,
        dateOfBirth = dateOfBirth,
        gender = gender,
        school = school,
        grade = grade,
        status = status
    )
}

data class GetStudentsResponse(
    override val number: Int,
    override val size: Int,
    override val numberOfElements: Int,
    override val totalPages: Int,
    override val totalElements: Long,
    override val first: Boolean,
    override val last: Boolean,
    override val empty: Boolean,
    override val content: MutableList<StudentModel>
) : PageableModel<StudentModel>(number, size, numberOfElements, totalPages, totalElements, first, last, empty, content) {
    companion object {
        fun of(request: ServerRequest, content: List<StudentModel>, totalSize: Long): GetStudentsResponse {
            val pageable = PageImpl(content, toPageRequest(request), totalSize)
            return GetStudentsResponse(
                number = pageable.number,
                size = pageable.size,
                numberOfElements = pageable.numberOfElements,
                totalPages = pageable.totalPages,
                totalElements = pageable.totalElements,
                first = pageable.isFirst,
                last = pageable.isLast,
                empty = pageable.isEmpty,
                content = pageable.content
            )
        }
    }
}