package com.ems.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@EnableR2dbcRepositories
@SpringBootApplication
class DemoEmsCoreApplication

fun main(args: Array<String>) {
    runApplication<DemoEmsCoreApplication>(*args)
}
