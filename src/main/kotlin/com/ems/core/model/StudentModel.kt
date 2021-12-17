package com.ems.core.model

import com.ems.core.entity.Students

data class GetStudentsResponse(
    val students: MutableList<Students>
)