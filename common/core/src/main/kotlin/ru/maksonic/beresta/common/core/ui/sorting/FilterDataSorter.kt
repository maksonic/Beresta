package ru.maksonic.beresta.common.core.ui.sorting

/**
 * @Author maksonic on 16.10.2023
 */
interface FilterDataSorter<T> {
    val sortedByFilterList: List<T>
    val isEmptyList: Boolean
}