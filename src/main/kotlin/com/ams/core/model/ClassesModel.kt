package com.ams.core.model

import com.ams.core.common.COMMA
import com.ams.core.common.enumerate.ClassStatusEnum
import com.ams.core.common.enumerate.DayOfWeekEnum
import com.ams.core.common.base.BaseModel
import com.ams.core.common.base.PageableModel
import com.ams.core.entity.ClassSchedules
import com.ams.core.entity.Classes
import reactor.util.function.Tuple2
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class ClassesModel(
    val id: Long,
    val teacherId: Long?,
    val name: String?,
    val type: String?,
    val startDate: String?,
    val endDate: String?,
    val dayOfWeek: List<DayOfWeekModel>?,
    val weeklyRepeat: Int?,
    val timeDuration: String?,
    val status: ClassStatusEnum? = ClassStatusEnum.READY,
    var schedules: List<ClassSchedulesModel>? = null
) {

    companion object : BaseModel<Classes, ClassesModel>() {

        override fun of(entity: Classes) =
            ClassesModel(
                id = entity.id!!,
                name = entity.name,
                type = entity.type,
                startDate = entity.startDate.format(DateTimeFormatter.ISO_DATE),
                endDate = entity.endDate.format(DateTimeFormatter.ISO_DATE),
                dayOfWeek = entity.getDayOfWeek(),
                weeklyRepeat = entity.weeklyRepeat,
                status = entity.status,
                timeDuration = entity.timeDuration,
                teacherId = entity.teacherId
            )

        fun of(tuple: Tuple2<Classes, List<ClassSchedules>>): ClassesModel =
            of(entity = tuple.t1).apply { this.schedules = tuple.t2.ifEmpty { null }?.map(ClassSchedulesModel::of) }

    }

    fun toEntity() = Classes(
        name = name!!,
        type = type!!,
        startDate = LocalDate.parse(startDate!!),
        endDate = LocalDate.parse(endDate!!),
        dayOfWeek = getDayOfWeek()!!,
        weeklyRepeat = weeklyRepeat!!,
        status = status!!,
        timeDuration = timeDuration!!,
        teacherId = teacherId!!
    )

    fun getDayOfWeek() = this.dayOfWeek?.joinToString(COMMA, transform = DayOfWeekModel::toString)

}

class GetClassesResponse : PageableModel<ClassesModel>()

data class DayOfWeekModel(

    val day: DayOfWeekEnum,
    val hour: String,
    val minute: String

) {

    companion object {
        fun parse(value: String) =
            value.split(":").let {
                DayOfWeekModel(
                    day = DayOfWeekEnum.valueOf(it[0]),
                    hour = it[1],
                    minute = it[2]
                )
            }
    }

    override fun toString() = "${this.day.name}:$hour:$minute"

}