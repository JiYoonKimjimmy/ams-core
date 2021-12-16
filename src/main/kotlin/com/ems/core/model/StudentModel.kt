package com.ems.core.model

import com.ems.core.entity.Student

data class GetStudentsResponse(
    val students: MutableList<Student>
)