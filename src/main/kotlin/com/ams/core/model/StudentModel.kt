package com.ams.core.model

import com.ams.core.common.enum.GenderEnum
import com.ams.core.common.enum.StatusEnum
import com.ams.core.entity.Parents
import com.ams.core.entity.Student
import org.springframework.data.domain.PageImpl
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.util.function.Tuple2
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class StudentModel(
    val id: Long,
    @get:NotNull
    val name: String?,
    @get:Size(min = 9, max = 11)
    val mobileNumber: String?,
    @get:Size(max = 8)
    val dateOfBirth: String?,
    val gender: GenderEnum?,
    val school: String?,
    val grade: String?,
    val status: StatusEnum?,
    var parents: List<Parents>? = null
) {
    companion object {

        fun of(student: Student) =
            StudentModel(
                id = student.id!!,
                name = student.name,
                mobileNumber = student.mobileNumber,
                dateOfBirth = student.dateOfBirth,
                gender = student.gender,
                school = student.school,
                grade = student.grade,
                status = student.status
            )

        fun of(tuple: Tuple2<Student, List<Parents>>): StudentModel =
            of(student = tuple.t1)
                .apply { this.parents = tuple.t2.ifEmpty { null } }

        fun of(request: ServerRequest, tuple: Tuple2<List<Student>, Long>) =
            tuple
                .t1
                .map { of(student = it) }
                .let { PageableModel(pageable = PageImpl(it, PageableModel.toPageRequest(request), tuple.t2)) }

    }

    fun toEntity() = Student(
        name = name!!,
        mobileNumber = mobileNumber!!,
        dateOfBirth = dateOfBirth!!,
        gender = gender!!,
        school = school!!,
        grade = grade!!
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
) : PageableModel<StudentModel>(number, size, numberOfElements, totalPages, totalElements, first, last, empty, content)