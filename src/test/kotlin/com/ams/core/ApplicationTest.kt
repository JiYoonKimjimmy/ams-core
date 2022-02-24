package com.ams.core

import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ApplicationTest {

    @Test
    fun localDateTimeTest() {
        val text = "2022-02-24"
        val localDate = LocalDate.parse(text, DateTimeFormatter.ISO_DATE)
        println(localDate)
    }

}