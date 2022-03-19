package com.ams.core.handler

import com.ams.core.common.enum.ClassScheduleTypeEnum
import com.ams.core.common.enum.ClassStatusEnum
import com.ams.core.common.enum.DayOfWeekEnum
import com.ams.core.entity.ClassSchedules
import com.ams.core.entity.Classes
import com.ams.core.repository.ClassSchedulesRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class ClassSchedulesHandler(
    val classSchedulesRepository: ClassSchedulesRepository
) {

    fun save(classes: Classes): Flux<ClassSchedules> {
        val dayOfWeeks = classes.getDayOfWeek()
        val startDate = classes.startDate
        val endDate = classes.endDate
        val days = ChronoUnit.DAYS.between(startDate, endDate)
        val classSchedules = (0..days)
            .asSequence()
            .map {
                val day = startDate.plusDays(it)
                val dayOfWeek = day.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US)
                Pair(dayOfWeek, day)
            }.filter {
                dayOfWeeks.contains(DayOfWeekEnum.valueOf(it.first.uppercase()))
            }.map {
                val day = it.second
                ClassSchedules(
                    type = ClassScheduleTypeEnum.REGULAR.name,
                    year = day.year.toString(),
                    month = "${if (day.monthValue < 10) "0" else ""}${day.monthValue}",
                    day = "${if (day.dayOfMonth < 10) "0" else ""}${day.dayOfMonth}",
                    status = ClassStatusEnum.READY.name,
                    classId = classes.id!!
                )
            }.toList()
        return classSchedulesRepository.saveAll(classSchedules)
    }

}