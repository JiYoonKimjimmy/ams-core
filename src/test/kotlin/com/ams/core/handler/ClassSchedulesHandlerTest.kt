package com.ams.core.handler

import com.ams.core.common.COMMA
import com.ams.core.common.enum.ClassScheduleTypeEnum
import com.ams.core.common.enum.ClassStatusEnum
import com.ams.core.common.enum.DayOfWeekEnum
import com.ams.core.entity.ClassSchedules
import com.ams.core.model.DayOfWeekModel
import com.ams.core.model.addPreZero
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.*
import me.moallemi.tools.daterange.localdate.rangeTo

class ClassSchedulesHandlerTest {

    @Test
    fun localDateTimeTest() {
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val date = LocalDateTime.parse("2022-03-01 13:00", format)
        println(date.year.toString())
        println(date.monthValue.addPreZero())
        println(date.dayOfMonth.addPreZero())
        println(date.toLocalTime().hour.addPreZero())
        println(date.toLocalTime().minute.addPreZero())
    }

    @Test
    fun betweenLocalDateTest() {
        val startDate = LocalDate.parse("2022-03-01")
        val endDate = LocalDate.parse("2022-03-31")
        for (date in startDate..endDate) {
            println(date)
        }
    }

    @Test
    fun saveClassSchedules() {
        val dayOfWeeks = "MON:14:30".split(COMMA).map(DayOfWeekModel::parse)
        val startDate = LocalDate.parse("2022-03-01").atTime(0, 0)
        val endDate = LocalDate.parse("2022-03-31").atTime(23, 59)
        val days = ChronoUnit.DAYS.between(startDate, endDate)
        val schedules = (0..days)
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
                    classId = 1
                )
            }.toList()
        println(schedules)
    }

}