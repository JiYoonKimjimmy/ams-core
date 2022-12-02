package com.ams.core.entity

import com.ams.core.common.addPreZero
import com.ams.core.common.base.BaseEntity
import com.ams.core.common.enumerate.ClassScheduleTypeEnum
import com.ams.core.common.enumerate.ClassStatusEnum
import com.ams.core.model.ClassSchedulesModel
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Table("CLASS_SCHEDULES")
data class ClassSchedules(

    @Id
    val id: Long? = null,
    var classId: Long,
    var type: String,
    var year: String,
    var month: String,
    var day: String,
    var hour: String,
    var minute: String,
    var timeDuration: String? = "",
    var status: String

) : BaseEntity<ClassSchedules, ClassSchedulesModel>() {

    companion object {
        fun toReady(classes: Classes, dateTime: LocalDateTime) =
            ClassSchedules(
                type = ClassScheduleTypeEnum.REGULAR.name,
                year = dateTime.year.toString(),
                month = dateTime.monthValue.addPreZero(),
                day = dateTime.dayOfMonth.addPreZero(),
                hour = dateTime.hour.addPreZero(),
                minute = dateTime.minute.addPreZero(),
                timeDuration = classes.timeDuration,
                status = ClassStatusEnum.READY.name,
                classId = classes.teacherId
            )
    }

    override fun update(model: ClassSchedulesModel) =
        this.apply {
            type = model.type ?: type
            year = model.year ?: year
            month = model.month ?: month
            day = model.day ?: day
            hour = model.hour ?: hour
            minute = model.minute ?: minute
            timeDuration = model.timeDuration ?: timeDuration
            status = model.status ?: status
        }.let { Mono.just(it) }

}
