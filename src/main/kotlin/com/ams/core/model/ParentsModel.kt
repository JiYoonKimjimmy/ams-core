package com.ams.core.model

import com.ams.core.common.enum.GenderEnum
import com.ams.core.common.enum.StatusEnum
import com.ams.core.entity.Parents
import org.springframework.data.domain.PageImpl
import org.springframework.web.reactive.function.server.ServerRequest

data class ParentsModel(
    var id: Long?,
    var name: String?,
    var mobileNumber: String?,
    var gender: GenderEnum?,
    var status: StatusEnum?,
    var studentId: Long
) {
    companion object {
        fun of(parents: Parents) = ParentsModel(
            id = parents.id,
            name = parents.name,
            mobileNumber = parents.mobileNumber,
            gender = parents.gender,
            status = parents.status,
            studentId = parents.studentId
        )
    }

    fun toEntity() = Parents(
        id = id,
        name = name,
        mobileNumber = mobileNumber,
        gender = gender,
        status = status,
        studentId = studentId
    )
}

data class GetParentsResponse(
    override val number: Int,
    override val size: Int,
    override val numberOfElements: Int,
    override val totalPages: Int,
    override val totalElements: Long,
    override val first: Boolean,
    override val last: Boolean,
    override val empty: Boolean,
    override val content: MutableList<ParentsModel>
) : PageableModel<ParentsModel>(number, size, numberOfElements, totalPages, totalElements, first, last, empty, content) {
    companion object {
        fun of(request: ServerRequest, content: List<ParentsModel>, totalSize: Long): GetParentsResponse {
            val pageable = PageImpl(content, toPageRequest(request), totalSize)
            return GetParentsResponse(
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