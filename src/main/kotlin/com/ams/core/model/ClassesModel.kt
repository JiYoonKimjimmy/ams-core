package com.ams.core.model

import com.ams.core.common.enum.ClassStatusEnum
import com.ams.core.common.enum.DayOfTheWeekEnum
import com.ams.core.entity.Classes
import org.springframework.data.domain.PageImpl
import org.springframework.web.reactive.function.server.ServerRequest
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class ClassesModel(

    val id: Long,
    val name: String?,
    val type: String?,
    val startDate: String?,
    val endDate: String?,
    val dayOfTheWeek: DayOfTheWeekEnum?,
    val weeklyRepeat: Int?,
    val status: ClassStatusEnum?,
    val teacherId: Long?

) {
    companion object {
        fun of(classes: Classes) = ClassesModel(
            id = classes.id!!,
            name = classes.name,
            type = classes.type,
            startDate = classes.startDate.format(DateTimeFormatter.ISO_DATE),
            endDate = classes.endDate.format(DateTimeFormatter.ISO_DATE),
            dayOfTheWeek = classes.dayOfTheWeek,
            weeklyRepeat = classes.weeklyRepeat,
            status = classes.status,
            teacherId = classes.teacherId
        )

        fun of(request: ServerRequest, content: List<ClassesModel>, totalSize: Long) =
            PageableModel(PageImpl(content, PageableModel.toPageRequest(request), totalSize))
    }

    fun toEntity() = Classes(
        name = name!!,
        type = type!!,
        startDate = LocalDate.parse(startDate!!),
        endDate = LocalDate.parse(endDate!!),
        dayOfTheWeek = dayOfTheWeek!!,
        weeklyRepeat = weeklyRepeat!!,
        status = status!!,
        teacherId = teacherId!!
    )

}

data class GetClassesResponse(

    override val number: Int,
    override val size: Int,
    override val numberOfElements: Int,
    override val totalPages: Int,
    override val totalElements: Long,
    override val first: Boolean,
    override val last: Boolean,
    override val empty: Boolean,
    override val content: MutableList<ClassesModel>

) : PageableModel<ClassesModel>(number, size, numberOfElements, totalPages, totalElements, first, last, empty, content)