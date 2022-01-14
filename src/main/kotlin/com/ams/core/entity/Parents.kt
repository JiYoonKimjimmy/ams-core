package com.ams.core.entity

import com.ams.core.common.enum.GenderEnum
import com.ams.core.common.enum.StatusEnum
import com.ams.core.model.ParentsModel
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import reactor.core.publisher.Mono

@Table("PARENTS")
class Parents(
    @Id
    @Column("ID")
    val id: Long?,
    @Column("NAME")
    var name: String,
    @Column("MOBILE_NUMBER")
    var mobileNumber: String,
    @Column("GENDER")
    var gender: GenderEnum,
    @Column("STATUS")
    var status: StatusEnum = StatusEnum.ACTIVE,
    @Column("STUDENT_ID")
    var studentId: Long
) : BaseEntity() {

    fun update(request: ParentsModel) = Mono.just(
        this.apply {
            name = request.name ?: name
            mobileNumber = request.mobileNumber ?: mobileNumber
            gender = request.gender ?: gender
            status = request.status ?: status
            studentId = request.studentId ?: studentId
        })

}