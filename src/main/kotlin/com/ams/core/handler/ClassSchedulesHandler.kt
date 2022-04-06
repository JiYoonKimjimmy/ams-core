package com.ams.core.handler

import com.ams.core.common.enum.DayOfWeekEnum
import com.ams.core.entity.ClassSchedules
import com.ams.core.entity.Classes
import com.ams.core.entity.getDisplayDayOfWeek
import com.ams.core.model.ClassSchedulesModel
import com.ams.core.model.ClassesModel
import com.ams.core.model.DayOfWeekModel
import com.ams.core.repository.ClassSchedulesRepository
import me.moallemi.tools.daterange.localdate.rangeTo
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class ClassSchedulesHandler(
    val classSchedulesRepository: ClassSchedulesRepository
) {

    /**
     * class schedules 조회
     */
    fun getAll(classes: ClassesModel): Mono<ClassesModel> =
        classSchedulesRepository
            .findAllByClassId(classes.id)
            .collectList()
            .map {
                classes.apply { schedules = it.map { s -> ClassSchedulesModel.of(s) } }
            }

    /**
     * class schedules 저장 처리
     */
    fun saveAll(classes: Classes): Flux<ClassSchedules> =
        classes.getDayOfWeek().let { dayOfWeeks ->
            (classes.startDate..classes.endDate)
                .filter {
                    dayOfWeeks.map(DayOfWeekModel::day).contains(DayOfWeekEnum.valueOf(it.getDisplayDayOfWeek()))
                }.map {
                    dayOfWeeks.find { c -> c.day.name == it.getDisplayDayOfWeek() }!!.let { c ->
                        it.atTime(c.hour.toInt(), c.minute.toInt())
                    }
                }.map {
                    ClassSchedules.ready(classes, it)
                }
        }.let {
            classSchedulesRepository.saveAll(it)
        }

}