package io.herain.moneytou.shared.jpa.support

import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.support.DefaultTransactionDefinition
import org.springframework.transaction.support.TransactionTemplate

class DefaultTransactionExecutor(
    private val platformTransactionManager: PlatformTransactionManager
) : TransactionExecutor {

    override fun <T> executeReadonly(supplier: () -> T): T {
        return this.executeReadonly(supplier, Propagation.REQUIRED)
    }

    override fun <T> executeReadonly(supplier: () -> T, propagation: Propagation): T {
        val transactionDefinition = DefaultTransactionDefinition(propagation.value())
        transactionDefinition.isReadOnly = true
        val template = TransactionTemplate(platformTransactionManager, transactionDefinition)

        return template.execute { supplier.invoke() }!!
    }

    override fun executeReadonly(runnable: () -> Unit) {
        this.executeReadonly(runnable, Propagation.REQUIRED)
    }

    override fun executeReadonly(runnable: () -> Unit, propagation: Propagation) {
        val transactionDefinition = DefaultTransactionDefinition(propagation.value())
        transactionDefinition.isReadOnly = true
        val template = TransactionTemplate(platformTransactionManager, transactionDefinition)

        return template.executeWithoutResult { runnable.invoke() }
    }

    override fun <T> execute(supplier: () -> T): T {
        return this.execute(supplier, Propagation.REQUIRED)
    }

    override fun <T> execute(supplier: () -> T, propagation: Propagation): T {
        val transactionDefinition = DefaultTransactionDefinition(propagation.value())
        val template = TransactionTemplate(platformTransactionManager, transactionDefinition)

        return template.execute { supplier.invoke() }!!
    }

    override fun execute(runnable: () -> Unit) {
        return this.execute(runnable, Propagation.REQUIRED)
    }

    override fun execute(runnable: () -> Unit, propagation: Propagation) {
        val transactionDefinition = DefaultTransactionDefinition(propagation.value())
        val template = TransactionTemplate(platformTransactionManager, transactionDefinition)

        return template.executeWithoutResult { runnable.invoke() }
    }
}
