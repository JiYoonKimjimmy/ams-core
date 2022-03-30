package com.ams.core.handler

import com.ams.core.common.enum.ClassScheduleTypeEnum
import com.ams.core.common.enum.ClassStatusEnum
import com.ams.core.common.enum.DayOfWeekEnum
import com.ams.core.entity.ClassSchedules
import com.ams.core.entity.Classes
import com.ams.core.model.DayOfWeekModel
import com.ams.core.model.addPreZero
import com.ams.core.model.getDisplayDayOfWeek
import com.ams.core.repository.ClassSchedulesRepository
import me.moallemi.tools.daterange.localdate.rangeTo
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class ClassSchedulesHandler(
    val classSchedulesRepository: ClassSchedulesRepository
) {

    /**
     * class schedules 저장 처리
     */
    fun save(classes: Classes): Flux<ClassSchedules> =
        classes.getDayOfWeek().let { dayOfWeeks ->
            (classes.startDate..classes.endDate)
                .filter {
                    dayOfWeeks.map(DayOfWeekModel::day).contains(DayOfWeekEnum.valueOf(it.getDisplayDayOfWeek()))
                }.map {
                    dayOfWeeks.find { c -> c.day.name == it.getDisplayDayOfWeek() }!!.let { c ->
                        it.atTime(c.hour.toInt(), c.minute.toInt())
                    }
                }.map {
                    ClassSchedules(
                        type = ClassScheduleTypeEnum.REGULAR.name,
                        year = it.year.toString(),
                        month = it.monthValue.addPreZero(),
                        day = it.dayOfMonth.addPreZero(),
                        hour = it.hour.addPreZero(),
                        minute = it.minute.addPreZero(),
                        status = ClassStatusEnum.READY.name,
                        classId = classes.teacherId
                    )
                }
        }.let {
            classSchedulesRepository.saveAll(it)
        }

}