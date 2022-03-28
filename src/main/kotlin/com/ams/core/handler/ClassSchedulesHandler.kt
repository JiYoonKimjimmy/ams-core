package com.ams.core.handler

import com.ams.core.common.enum.ClassScheduleTypeEnum
import com.ams.core.common.enum.ClassStatusEnum
import com.ams.core.common.enum.DayOfWeekEnum
import com.ams.core.entity.ClassSchedules
import com.ams.core.entity.Classes
import com.ams.core.model.DayOfWeekModel
import com.ams.core.model.addPreZero
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

    /**
     * class schedules 저장 처리
     */
    fun save(classes: Classes): Flux<ClassSchedules> {
        val dayOfWeeks = classes.getDayOfWeek()
        val startDate = classes.startDate.atTime(0, 0)
        val endDate = classes.endDate.atTime(23, 59)
        val days = ChronoUnit.DAYS.between(startDate, endDate)
        val classSchedules = (0..days)
            .asSequence()
            .map {
                val date = startDate.plusDays(it)
                Pair(date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US).uppercase(Locale.getDefault()), date)
            }.filter {
                dayOfWeeks.map(DayOfWeekModel::day).contains(DayOfWeekEnum.valueOf(it.first))
            }.map {
                val classDateTime = dayOfWeeks.find { d -> d.day == DayOfWeekEnum.valueOf(it.first) }!!.let { d ->
                    it.second.plusHours(d.hour.toLong()).plusMinutes(d.minute.toLong())
                }
                ClassSchedules(
                    type = ClassScheduleTypeEnum.REGULAR.name,
                    year = classDateTime.year.toString(),
                    month = classDateTime.monthValue.addPreZero(),
                    day = classDateTime.dayOfMonth.addPreZero(),
                    hour = classDateTime.hour.addPreZero(),
                    minute = classDateTime.minute.addPreZero(),
                    status = ClassStatusEnum.READY.name,
                    classId = classes.teacherId
                )
            }.toList()

        return classSchedulesRepository.saveAll(classSchedules)
    }

}