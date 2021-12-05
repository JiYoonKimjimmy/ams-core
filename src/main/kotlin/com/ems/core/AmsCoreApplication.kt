package com.ems.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
class AmsCoreApplication

fun main(args: Array<String>) {
    runApplication<AmsCoreApplication>(*args)
}