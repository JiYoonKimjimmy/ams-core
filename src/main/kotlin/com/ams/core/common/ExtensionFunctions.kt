package com.ams.core.common

import org.springframework.data.domain.PageRequest
import org.springframework.web.reactive.function.server.ServerRequest
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

fun ServerRequest.toPageRequest() =
    PageRequest.of(
        this.queryParam("number").orElse("0").toInt(),
        this.queryParam("size").orElse("10").toInt()
    )

fun LocalDate.getDisplayDayOfWeek() = this.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US).uppercase()

fun Int.addPreZero() = if (this < 10) "0$this" else "$this"
