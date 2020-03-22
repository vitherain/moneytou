package io.herain.moneytou.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["io.herain.moneytou.**.repository"])
class MoneytouApplication

fun main(args: Array<String>) {
    runApplication<MoneytouApplication>(*args)
}
