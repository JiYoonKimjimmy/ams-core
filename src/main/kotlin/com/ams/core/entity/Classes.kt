package com.ams.core.entity

import com.ams.core.common.enum.DayOfTheWeekEnum
import com.ams.core.common.enum.StatusEnum
import com.ams.core.model.ClassesModel
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import reactor.core.publisher.Mono
import java.time.LocalDate

@Table("CLASSES")
data class Classes(

    @Id
    val id: Long? = null,
    var name: String,
    var type: String,
    var startDate: LocalDate = LocalDate.now(),
    var endDate: LocalDate = LocalDate.now(),
    var dayOfTheWeek: DayOfTheWeekEnum,
    var weeklyRepeat: Int = 1,
    var status: StatusEnum,
    var teacherId: Long

) : BaseEntity() {

    fun update(request: ClassesModel) = Mono.just(
        this.apply {
            name = request.name ?: name
            type = request.type ?: type
            startDate = convertDate(request.startDate, startDate)
            endDate = convertDate(request.endDate, endDate)
            dayOfTheWeek = request.dayOfTheWeek ?: dayOfTheWeek
            weeklyRepeat = request.weeklyRepeat ?: weeklyRepeat
            status = request.status ?: status
            teacherId = request.teacherId ?: teacherId
        })

    private fun convertDate(request: String?, defaultDate: LocalDate) = request.let { LocalDate.parse(it) ?: defaultDate }

}