package com.ams.core.entity

import com.ams.core.common.base.BaseEntity
import com.ams.core.common.enumerate.StatusEnum
import com.ams.core.model.TeacherModel
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import reactor.core.publisher.Mono

@Table("TEACHERS")
data class Teacher(

    @Id
    val id: Long? = null,
    var name: String,
    var mobileNumber: String,
    var status: StatusEnum = StatusEnum.ACTIVE

) : BaseEntity<Teacher, TeacherModel>() {

    override fun update(model: TeacherModel) =
        this.apply {
            name = model.name ?: name
            mobileNumber = model.mobileNumber ?: mobileNumber
            status = model.status ?: status
        }.let { Mono.just(it) }

}
