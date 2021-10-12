package com.ems.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoEmsCoreApplication

fun main(args: Array<String>) {
    runApplication<DemoEmsCoreApplication>(*args)
}