package io.herain.moneytou.common.account.repository

import io.herain.moneytou.common.account.domain.Account
import org.springframework.data.repository.Repository
import java.util.UUID

interface AccountRepository : Repository<Account, UUID>, AccountCheckOperations
