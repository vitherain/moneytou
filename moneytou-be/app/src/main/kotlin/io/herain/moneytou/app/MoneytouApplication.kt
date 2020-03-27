package io.herain.moneytou.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(basePackages = ["io.herain.moneytou.**"])
@EnableJpaRepositories(basePackages = ["io.herain.moneytou.**.repository"])
class MoneytouApplication

fun main(args: Array<String>) {
    runApplication<MoneytouApplication>(*args)
}
