package io.herain.moneytou.tx.support

import io.herain.moneytou.common.category.domain.Category
import io.herain.moneytou.common.category.repository.CategoryRepository
import io.herain.moneytou.test.repository.InMemoryCrudRepository
import java.util.UUID

class InMemoryCategoryRepository(
    existingData: List<Category>?
) : CategoryRepository, InMemoryCrudRepository<Category>(existingData) {

    override fun findTransferCategoryId(): UUID {
        return db.values.find { it.code == Category.CategoryCode("TRANSFER") }!!.id
    }
}
