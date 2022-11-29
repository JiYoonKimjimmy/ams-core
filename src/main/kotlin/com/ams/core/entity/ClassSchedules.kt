package com.ams.core.entity

import com.ams.core.common.enumerate.ClassScheduleTypeEnum
import com.ams.core.common.enumerate.ClassStatusEnum
import com.ams.core.model.ClassSchedulesModel
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.*

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

) : BaseEntity() {
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

    fun update(request: ClassSchedulesModel) = Mono.just(
        this.apply {
            type = request.type ?: type
            year = request.year ?: year
            month = request.month ?: month
            day = request.day ?: day
            hour = request.hour ?: hour
            minute = request.minute ?: minute
            timeDuration = request.timeDuration ?: timeDuration
            status = request.status ?: status
        })

}

fun LocalDate.getDisplayDayOfWeek() = this.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US).uppercase()
fun Int.addPreZero() = if (this < 10) "0$this" else "$this"