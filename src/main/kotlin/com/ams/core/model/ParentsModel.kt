package com.ams.core.model

import com.ams.core.common.enumerate.GenderEnum
import com.ams.core.common.enumerate.StatusEnum
import com.ams.core.common.base.BaseModel
import com.ams.core.common.base.PageableModel
import com.ams.core.entity.Parents

class ParentsModel(

    val id: Long = 0,
    val name: String? = null,
    val mobileNumber: String? = null,
    val gender: GenderEnum? = null,
    val status: StatusEnum? = null,
    val studentId: Long? = null

) {

    companion object: BaseModel<Parents, ParentsModel>() {
        override fun of(entity: Parents) =
            ParentsModel(
                id = entity.id!!,
                name = entity.name,
                mobileNumber = entity.mobileNumber,
                gender = entity.gender,
                status = entity.status,
                studentId = entity.studentId
            )
    }

    fun toEntity() =
        Parents(
            name = name!!,
            mobileNumber = mobileNumber!!,
            gender = gender!!,
            studentId = studentId!!
        )

}

class GetParentsResponse : PageableModel<ParentsModel>()