package com.ams.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AmsCoreApplication

fun main(args: Array<String>) {
    runApplication<AmsCoreApplication>(*args)
}