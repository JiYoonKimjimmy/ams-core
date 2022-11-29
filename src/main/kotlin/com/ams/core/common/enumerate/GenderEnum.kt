package com.ams.core.common.enumerate

enum class GenderEnum(val value: String) {
    M("Male"),
    F("Female");

    companion object {
        fun find(value: String): GenderEnum = values().find { it.value == value }!!
    }
}