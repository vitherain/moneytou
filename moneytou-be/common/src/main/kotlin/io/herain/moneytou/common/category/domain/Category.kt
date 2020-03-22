package io.herain.moneytou.common.category.domain

import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "category", schema = "moneytou")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID,
    @Embedded
    val name: CategoryName,
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: CategoryStatus = CategoryStatus.ACTIVE,
    @Enumerated(EnumType.STRING)
    @Column(name = "icon", nullable = false)
    val icon: Icon,
    @OneToMany(cascade = [CascadeType.REMOVE], fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_category_id")
    val childCategories: List<Category>
) {
    @Embeddable
    data class CategoryName(
        @Column(name = "name", nullable = false)
        val name: String
    ) {
        init {
            if (name.length > 40) {
                throw IllegalArgumentException("name='${name}' must be 40 characters long at most")
            }
        }
    }

    enum class Icon(val iconCode: String) {
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
}
