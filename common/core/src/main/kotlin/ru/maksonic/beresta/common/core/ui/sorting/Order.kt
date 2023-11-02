package ru.maksonic.beresta.common.core.ui.sorting

/**
 * @Author maksonic on 16.10.2023
 */
enum class Order {
    ASCENDING, DESCENDING
}

val Order.isAscending get() = this == Order.ASCENDING
