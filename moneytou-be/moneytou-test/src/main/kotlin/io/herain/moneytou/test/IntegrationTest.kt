package io.herain.moneytou.test

import io.zonky.test.db.AutoConfigureEmbeddedDatabase

@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@AutoConfigureEmbeddedDatabase(beanName = "dataSource")
annotation class IntegrationTest
