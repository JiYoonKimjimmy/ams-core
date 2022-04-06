package com.ams.core.model

import com.ams.core.entity.ClassSchedules

data class ClassSchedulesModel(

    val id: Long?,
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
    companion object {
        fun of(classSchedules: ClassSchedules) = ClassSchedulesModel(
            id = classSchedules.id,
            type = classSchedules.type,
            year = classSchedules.year,
            month = classSchedules.month,
            day = classSchedules.day,
            hour = classSchedules.hour,
            minute = classSchedules.minute,
            timeDuration = classSchedules.timeDuration,
            status = classSchedules.status,
            classId = classSchedules.classId,
        )
    }
}