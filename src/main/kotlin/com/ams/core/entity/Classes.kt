package com.ams.core.entity

import com.ams.core.common.COMMA
import com.ams.core.common.base.BaseEntity
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

) : BaseEntity<Classes, ClassesModel>() {

    override fun update(model: ClassesModel) =
        this.apply {
            name = model.name ?: name
            type = model.type ?: type
            startDate = convertDate(model.startDate) ?: startDate
            endDate = convertDate(model.endDate) ?: endDate
            dayOfWeek = model.getDayOfWeek() ?: dayOfWeek
            weeklyRepeat = model.weeklyRepeat ?: weeklyRepeat
            status = model.status ?: status
            timeDuration = model.timeDuration ?: timeDuration
            teacherId = model.teacherId ?: teacherId
        }.let { Mono.just(it) }

    fun getDayOfWeek() = dayOfWeek.split(COMMA).map(DayOfWeekModel::parse)

    private fun convertDate(request: String?) = request?.let { LocalDate.parse(it) }

}
