package io.herain.moneytou.shared.jpa.support

import org.springframework.transaction.annotation.Propagation

interface TransactionExecutor {

    /**
     * Given lambda is executed in read-only transaction
     * with [org.springframework.transaction.annotation.Propagation.REQUIRED]
     * propagation behavior.
     */
    fun <T> executeReadonly(supplier: () -> T): T

    /**
     * Given lambda is executed in read-only transaction
     * with the specified propagation behavior.
     */
    fun <T> executeReadonly(supplier: () -> T, propagation: Propagation): T

    /**
     * Given lambda is executed in read-only transaction
     * with [org.springframework.transaction.annotation.Propagation.REQUIRED]
     * propagation behavior.
     */
    fun executeReadonly(runnable: () -> Unit)

    /**
     * Given lambda is executed in read-only transaction
     * with the specified propagation behavior.
     */
    fun executeReadonly(runnable: () -> Unit, propagation: Propagation)

    /**
     * Given lambda is executed in read-only transaction
     * with [org.springframework.transaction.annotation.Propagation.REQUIRED]
     * propagation behavior.
     */
    fun <T> execute(supplier: () -> T): T

    /**
     * Given lambda is executed in read-only transaction
     * with the specified propagation behavior.
     */
    fun <T> execute(supplier: () -> T, propagation: Propagation): T

    /**
     * Given lambda is executed in read-only transaction
     * with [org.springframework.transaction.annotation.Propagation.REQUIRED]
     * propagation behavior.
     */
    fun execute(runnable: () -> Unit)

    /**
     * Given lambda is executed in read-only transaction
     * with the specified propagation behavior.
     */
    fun execute(runnable: () -> Unit, propagation: Propagation)
}
