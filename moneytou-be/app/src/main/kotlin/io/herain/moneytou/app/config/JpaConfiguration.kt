package io.herain.moneytou.app.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration

@Configuration
@EntityScan("io.herain.moneytou.**.domain", "io.herain.moneytou.**.jpa")
class JpaConfiguration
