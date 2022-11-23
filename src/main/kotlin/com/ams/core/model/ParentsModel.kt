package com.ams.core.model

import com.ams.core.common.enum.GenderEnum
import com.ams.core.common.enum.StatusEnum
import com.ams.core.common.model.BaseModel
import com.ams.core.entity.Parents

class ParentsModel(

    val id: Long = 0,
    val name: String? = null,
    val mobileNumber: String? = null,
    val gender: GenderEnum? = null,
    val status: StatusEnum? = null,
    val studentId: Long? = null

) : BaseModel<Parents, ParentsModel>() {

    override fun of(entity: Parents): ParentsModel =
        ParentsModel(
            id = entity.id!!,
            name = entity.name,
            mobileNumber = entity.mobileNumber,
            gender = entity.gender,
            status = entity.status,
            studentId = entity.studentId
        )

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