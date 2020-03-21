package io.herain.moneytou.test

import io.zonky.test.db.AutoConfigureEmbeddedDatabase
import java.lang.annotation.Inherited

@Inherited
@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@AutoConfigureEmbeddedDatabase(beanName = "dataSource")
annotation class DbIntegrationTest
