package io.herain.moneytou.common.category.repository

import io.herain.moneytou.common.category.domain.Category
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface CategoryFetchingOperations {

    @Query("SELECT c.id FROM Category c WHERE c.code.code = '${Category.TRANSFER_CODE}'")
    fun findTransferCategoryId(): UUID
}
