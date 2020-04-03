package io.herain.moneytou.common.category.domain

import io.herain.moneytou.shared.domain.IdentifiedEntity
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "category", schema = "moneytou")
data class Category(
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    override val id: UUID = UUID.randomUUID(),
    @Embedded
    val code: CategoryCode,
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: CategoryStatus = CategoryStatus.ACTIVE,
    @Enumerated(EnumType.STRING)
    @Column(name = "icon", nullable = false)
    val icon: Icon,
    @OneToMany(cascade = [CascadeType.REMOVE], fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_category_id")
    val childCategories: List<Category>
) : IdentifiedEntity {
    @Embeddable
    data class CategoryCode(
        @Column(name = "code", nullable = false, unique = true)
        val code: String
    ) {
        init {
            if (code.length > 255) {
                throw IllegalArgumentException("code='${code}' is not valid")
            }
            if (!"^[A-Z]{1}[A-Z_0-9]*$".toRegex().matches(code)) {
                throw IllegalArgumentException("code='${code}' is not valid")
            }
        }
    }

    enum class Icon(val iconCode: String) {
        EXCHANGE_ALT("exchange-alt"),
        PIGGY_BANK("piggy-bank"),
        UNIVERSITY("university"),
        MONEY_BILL("money-bill"),
        EURO_SIGN("euro-sign"),
        DOLLAR_SIGN("dollar-sign"),
        COINS("coins"),
        CREDIT_CARD("credit-card")
    }

    enum class CategoryStatus {
        ACTIVE, INACTIVE
    }

    companion object {
        const val TRANSFER_CODE = "TRANSFER"
    }
}
