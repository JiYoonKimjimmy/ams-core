package com.ams.core.model

import com.ams.core.common.enum.StatusEnum
import com.ams.core.entity.Teacher
import org.springframework.data.domain.PageImpl
import org.springframework.web.reactive.function.server.ServerRequest

data class TeacherModel(
    val id: Long,
    val name: String?,
    val mobileNumber: String?,
    val status: StatusEnum?
) {
    companion object {
        fun of(teacher: Teacher) = TeacherModel(
            id = teacher.id!!,
            name = teacher.name,
            mobileNumber = teacher.mobileNumber,
            status = teacher.status
        )

        fun of(request: ServerRequest, content: List<TeacherModel>, totalSize: Long) =
            PageableModel(PageImpl(content, PageableModel.toPageRequest(request), totalSize))
    }

    fun toEntity() = Teacher(
        name = name!!,
        mobileNumber = mobileNumber!!,
        status = status!!
    )
}


data class GetTeachersResponse(
    override val number: Int,
    override val size: Int,
    override val numberOfElements: Int,
    override val totalPages: Int,
    override val totalElements: Long,
    override val first: Boolean,
    override val last: Boolean,
    override val empty: Boolean,
    override val content: List<TeacherModel>
) : PageableModel<TeacherModel>(number, size, numberOfElements, totalPages, totalElements, first, last, empty, content)