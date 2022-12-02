package com.ams.core.entity

import com.ams.core.common.base.BaseEntity
import com.ams.core.common.enumerate.GenderEnum
import com.ams.core.common.enumerate.StatusEnum
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

) : BaseEntity<Parents, ParentsModel>() {

    override fun update(model: ParentsModel) =
        this.apply {
            name = model.name ?: name
            mobileNumber = model.mobileNumber ?: mobileNumber
            gender = model.gender ?: gender
            status = model.status ?: status
            studentId = model.studentId ?: studentId
        }.let { Mono.just(it) }

}
