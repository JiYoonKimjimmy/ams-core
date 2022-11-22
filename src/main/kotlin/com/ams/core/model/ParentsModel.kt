package com.ams.core.model

import com.ams.core.common.enum.GenderEnum
import com.ams.core.common.enum.StatusEnum
import com.ams.core.entity.Parents
import org.springframework.data.domain.PageImpl
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.util.function.Tuple2

data class ParentsModel(

    val id: Long,
    val name: String?,
    val mobileNumber: String?,
    val gender: GenderEnum?,
    val status: StatusEnum?,
    val studentId: Long?

) {
    companion object {

        fun of(parents: Parents) =
            ParentsModel(
                id = parents.id!!,
                name = parents.name,
                mobileNumber = parents.mobileNumber,
                gender = parents.gender,
                status = parents.status,
                studentId = parents.studentId
            )

        fun of(request: ServerRequest, tuple: Tuple2<List<Parents>, Long>) =
            tuple
                .t1
                .map { of(parents = it) }
                .let { PageableModel(pageable = PageImpl(it, PageableModel.toPageRequest(request), tuple.t2)) }

    }

    fun toEntity() =
        Parents(
            name = name!!,
            mobileNumber = mobileNumber!!,
            gender = gender!!,
            studentId = studentId!!
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
    override val content: List<ParentsModel>

) : PageableModel<ParentsModel>(number, size, numberOfElements, totalPages, totalElements, first, last, empty, content)