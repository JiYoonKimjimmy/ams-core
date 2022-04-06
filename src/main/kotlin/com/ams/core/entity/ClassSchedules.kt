package com.ams.core.entity

import com.ams.core.common.enum.ClassScheduleTypeEnum
import com.ams.core.common.enum.ClassStatusEnum
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.*

@Table("CLASS_SCHEDULES")
data class ClassSchedules(

    @Id
    val id: Long? = null,
    val classId: Long,
    val type: String,
    val year: String,
    val month: String,
    val day: String,
    val hour: String,
    val minute: String,
    val timeDuration: String,
    val status: String

) : BaseEntity() {
    companion object {
        fun ready(classes: Classes, dateTime: LocalDateTime) =
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
}

fun LocalDate.getDisplayDayOfWeek() = this.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US).uppercase()
fun Int.addPreZero() = if (this < 10) "0$this" else "$this"