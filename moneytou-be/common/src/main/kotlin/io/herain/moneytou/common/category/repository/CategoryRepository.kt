package io.herain.moneytou.common.category.repository

import io.herain.moneytou.common.category.domain.Category
import org.springframework.data.repository.Repository
import java.util.UUID

interface CategoryRepository : Repository<Category, UUID>, CategoryFetchingOperations
