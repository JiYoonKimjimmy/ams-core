package com.ems.core.common.enum

enum class GenderEnum(val value: String) {
    M("Male"),
    F("Female");

    companion object {
        fun find(value: String): GenderEnum = values().find { it.value == value }!!
    }
}