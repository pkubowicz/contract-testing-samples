package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Runner

fun main(args: Array<String>) {
    SpringApplication.run(Runner::class.java, *args)
}
