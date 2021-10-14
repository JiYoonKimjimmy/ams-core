package com.ems.core

import com.ems.core.entity.GenderEnum
import com.ems.core.entity.StatusEnum
import com.ems.core.entity.Students
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoEmsCoreApplicationTests {

    @Test
    fun enumTest() {
        val student = Students(
            id = 1,
            gender = GenderEnum.Male.name,
            status = StatusEnum.ACTIVE
        )
        println(student)
        listOf(student)
            .map {
                it.gender = it.gender?.let { it1 -> GenderEnum.valueOf(it1).value }
            }
        println(student)
    }

}
