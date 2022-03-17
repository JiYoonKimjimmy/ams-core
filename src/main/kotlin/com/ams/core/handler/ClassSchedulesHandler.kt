package com.ams.core.handler

import com.ams.core.common.enum.DayOfWeekEnum
import com.ams.core.entity.ClassSchedules
import com.ams.core.entity.Classes
import com.ams.core.repository.ClassSchedulesRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class ClassSchedulesHandler(
    val classSchedulesRepository: ClassSchedulesRepository
) {

    fun save(classes: Classes): Mono<Classes> {
        val dayOfWeeks = classes.dayOfWeek.split(",").map(DayOfWeekEnum::valueOf)
        val startDate = classes.startDate
        val endDate = classes.endDate
        val days = ChronoUnit.DAYS.between(startDate, endDate)
        val classSchedules = (0..days)
            .asSequence()
            .map {
                startDate.plusDays(it)
            }.map {
                Pair(it.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US), it)
            }.filter {
                dayOfWeeks.contains(DayOfWeekEnum.valueOf(it.first.uppercase()))
            }.map {
                it.second
            }.map {
                ClassSchedules(
                    type = "REGULAR",
                    year = it.year.toString(),
                    month = "${if (it.monthValue < 10) "0" else ""}${it.monthValue}",
                    day = "${if (it.dayOfMonth < 10) "0" else ""}${it.dayOfMonth}",
                    status = "READY",
                    classId = classes.id!!
                )
            }.toList()
        println(classSchedules)
        classSchedulesRepository.saveAll(classSchedules)
        return Mono.just(classes)
    }

}