package com.ams.core.entity

import com.ams.core.common.COMMA
import com.ams.core.common.enumerate.ClassStatusEnum
import com.ams.core.model.ClassesModel
import com.ams.core.model.DayOfWeekModel
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import reactor.core.publisher.Mono
import java.time.LocalDate

@Table("CLASSES")
data class Classes(

    @Id
    val id: Long? = null,
    var teacherId: Long,
    var name: String,
    var type: String,
    var startDate: LocalDate = LocalDate.now(),
    var endDate: LocalDate = LocalDate.now(),
    var dayOfWeek: String,
    var weeklyRepeat: Int = 1,
    var timeDuration: String,
    var status: ClassStatusEnum = ClassStatusEnum.READY

) : BaseEntity() {

    fun getDayOfWeek() = dayOfWeek.split(COMMA).map(DayOfWeekModel::parse)

    fun update(request: ClassesModel) = Mono.just(
        this.apply {
            name = request.name ?: name
            type = request.type ?: type
            startDate = convertDate(request.startDate, startDate)
            endDate = convertDate(request.endDate, endDate)
            dayOfWeek = request.getDayOfWeek() ?: dayOfWeek
            weeklyRepeat = request.weeklyRepeat ?: weeklyRepeat
            status = request.status ?: status
            timeDuration = request.timeDuration ?: timeDuration
            teacherId = request.teacherId ?: teacherId
        })

    private fun convertDate(request: String?, defaultDate: LocalDate) = request?.let { LocalDate.parse(it) } ?: defaultDate

}