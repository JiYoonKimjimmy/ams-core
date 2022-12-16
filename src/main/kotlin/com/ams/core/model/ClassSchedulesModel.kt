package com.ams.core.model

import com.ams.core.common.base.BaseModel
import com.ams.core.common.base.PageableModel
import com.ams.core.entity.ClassSchedules

data class ClassSchedulesModel(

    val id: Long,
    val type: String?,
    val year: String?,
    val month: String?,
    val day: String?,
    val hour: String?,
    val minute: String?,
    val timeDuration: String?,
    val status: String?,
    val classId: Long?

) {
    companion object : BaseModel<ClassSchedules, ClassSchedulesModel>() {
        override fun of(entity: ClassSchedules) =
            ClassSchedulesModel(
                id = entity.id!!,
                type = entity.type,
                year = entity.year,
                month = entity.month,
                day = entity.day,
                hour = entity.hour,
                minute = entity.minute,
                timeDuration = entity.timeDuration,
                status = entity.status,
                classId = entity.classId,
            )
    }

    fun toEntity() =
        ClassSchedules(
            type = type!!,
            year = year!!,
            month = month!!,
            day = day!!,
            hour = hour!!,
            minute = minute!!,
            timeDuration = timeDuration!!,
            status = status!!,
            classId = classId!!,
        )

}

class GetClassSchedulesResponse : PageableModel<ClassSchedulesModel>()
