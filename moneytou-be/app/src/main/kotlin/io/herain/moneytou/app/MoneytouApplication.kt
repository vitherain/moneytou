package io.herain.moneytou.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MoneytouApplication

fun main(args: Array<String>) {
    runApplication<MoneytouApplication>(*args)
}
