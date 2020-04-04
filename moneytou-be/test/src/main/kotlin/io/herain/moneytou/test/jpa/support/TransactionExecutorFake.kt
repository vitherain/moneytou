package io.herain.moneytou.test.jpa.support

import io.herain.moneytou.shared.jpa.support.TransactionExecutor
import org.springframework.transaction.annotation.Propagation

class TransactionExecutorFake : TransactionExecutor {

    override fun <T> executeReadonly(supplier: () -> T): T {
        return supplier.invoke()
    }

    override fun <T> executeReadonly(supplier: () -> T, propagation: Propagation): T {
        return supplier.invoke()
    }

    override fun executeReadonly(runnable: () -> Unit) {
        runnable.invoke()
    }

    override fun executeReadonly(runnable: () -> Unit, propagation: Propagation) {
        runnable.invoke()
    }

    override fun <T> execute(supplier: () -> T): T {
        return supplier.invoke()
    }

    override fun <T> execute(supplier: () -> T, propagation: Propagation): T {
        return supplier.invoke()
    }

    override fun execute(runnable: () -> Unit) {
        runnable.invoke()
    }

    override fun execute(runnable: () -> Unit, propagation: Propagation) {
        runnable.invoke()
    }
}
