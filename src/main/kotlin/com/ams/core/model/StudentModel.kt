package com.ams.core.model

import com.ams.core.common.enum.GenderEnum
import com.ams.core.common.enum.StatusEnum
import com.ams.core.common.model.BaseModel
import com.ams.core.common.model.PageableModel
import com.ams.core.entity.Parents
import com.ams.core.entity.Student
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
    var parents: List<ParentsModel>? = null
) {
    companion object : BaseModel<Student, StudentModel>() {

        override fun of(entity: Student) =
            StudentModel(
                id = entity.id!!,
                name = entity.name,
                mobileNumber = entity.mobileNumber,
                dateOfBirth = entity.dateOfBirth,
                gender = entity.gender,
                school = entity.school,
                grade = entity.grade,
                status = entity.status
            )

        fun of(tuple: Tuple2<Student, List<Parents>>): StudentModel =
            of(entity = tuple.t1).apply { this.parents = tuple.t2.ifEmpty { null }?.map(ParentsModel::of) }

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

class GetStudentsResponse : PageableModel<StudentModel>()