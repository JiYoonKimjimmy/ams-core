package com.ams.core.entity

import com.ams.core.common.enum.GenderEnum
import com.ams.core.common.enum.StatusEnum
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import reactor.core.publisher.Mono

@Table("PARENTS")
class Parents(
    @Id
    @Column("ID")
    var id: Long?,
    @Column("NAME")
    var name: String?,
    @Column("MOBILE_NUMBER")
    var mobileNumber: String?,
    @Column("GENDER")
    var gender: GenderEnum?,
    @Column("STATUS")
    var status: StatusEnum? = StatusEnum.ACTIVE,
    @Column("STUDENT_ID")
    var studentId: Long
) : BaseEntity() {
    fun updateToMono(request: Parents): Mono<Parents> {
        this.name = request.name ?: this.name
        this.mobileNumber = request.mobileNumber ?: this.mobileNumber
        this.gender = request.gender ?: this.gender
        this.status = request.status ?: this.status
        this.studentId = request.studentId
        return Mono.just(this)
    }
}