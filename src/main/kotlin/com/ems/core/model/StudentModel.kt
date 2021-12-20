package com.ems.core.model

import com.ems.core.common.enum.GenderEnum
import com.ems.core.common.enum.StatusEnum
import com.ems.core.entity.Student
import org.springframework.data.annotation.Id
import org.springframework.data.domain.PageImpl
import org.springframework.data.relational.core.mapping.Column
import org.springframework.web.reactive.function.server.ServerRequest

data class StudentModel(
    var id: Long?,
    var name: String?,
    var mobileNumber: String?,
    var dateOfBirth: String?,
    var gender: GenderEnum?,
    var school: String?,
    var grade: String?,
    var status: StatusEnum?
) {
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
    override val content: MutableList<Student>
) : PageableModel<Student>(number, size, numberOfElements, totalPages, totalElements, first, last, empty, content) {
    companion object {
        fun of(request: ServerRequest, content: List<Student>, totalSize: Long): GetStudentsResponse {
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