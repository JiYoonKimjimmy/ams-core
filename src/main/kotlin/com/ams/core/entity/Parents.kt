package com.ams.core.entity

import com.ams.core.common.enum.GenderEnum
import com.ams.core.common.enum.StatusEnum
import com.ams.core.model.ParentsModel
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import reactor.core.publisher.Mono

@Table("PARENTS")
data class Parents(

    @Id
    val id: Long? = null,
    var studentId: Long,
    var name: String,
    var mobileNumber: String,
    var gender: GenderEnum,
    var status: StatusEnum = StatusEnum.ACTIVE

) : BaseEntity() {

    fun update(request: ParentsModel) =
        this.apply {
            name = request.name ?: name
            mobileNumber = request.mobileNumber ?: mobileNumber
            gender = request.gender ?: gender
            status = request.status ?: status
            studentId = request.studentId ?: studentId
        }.let { Mono.just(it) }

}