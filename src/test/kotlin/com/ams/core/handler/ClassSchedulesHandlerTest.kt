package com.ams.core.handler

import com.ams.core.common.enum.DayOfWeekEnum
import com.ams.core.entity.ClassSchedules
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.*

class ClassSchedulesHandlerTest {

    @Test
    fun saveClassSchedules() {
        val dayOfWeeks = "MON,WED".split(",").map(DayOfWeekEnum::valueOf)
        val startDate = LocalDate.parse("2022-03-01")
        val endDate = LocalDate.parse("2022-03-31")
        val days = ChronoUnit.DAYS.between(startDate, endDate)
        val schedules = (0..days).map {
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
                status = "READY"
            )
        }
        println(schedules)
    }

}