package com.ams.core.model

import com.ams.core.common.enumerate.StatusEnum
import com.ams.core.common.base.BaseModel
import com.ams.core.common.base.PageableModel
import com.ams.core.entity.Teacher

data class TeacherModel(

    val id: Long,
    val name: String?,
    val mobileNumber: String?,
    val status: StatusEnum?

) {
    companion object : BaseModel<Teacher, TeacherModel>() {
        override fun of(entity: Teacher) = TeacherModel(
            id = entity.id!!,
            name = entity.name,
            mobileNumber = entity.mobileNumber,
            status = entity.status
        )
    }

    fun toEntity() = Teacher(
        name = name!!,
        mobileNumber = mobileNumber!!,
        status = status!!
    )
}

//data class GetTeachersResponse(
//    override val number: Int,
//    override val size: Int,
//    override val numberOfElements: Int,
//    override val totalPages: Int,
//    override val totalElements: Long,
//    override val first: Boolean,
//    override val last: Boolean,
//    override val empty: Boolean,
//    override val content: List<TeacherModel>
//) : PageableModel<TeacherModel>(number, size, numberOfElements, totalPages, totalElements, first, last, empty, content)

class GetTeachersResponse : PageableModel<TeacherModel>()
