package com.example.aoptest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AopTestApplication

fun main(args: Array<String>) {
    runApplication<AopTestApplication>(*args)
}
