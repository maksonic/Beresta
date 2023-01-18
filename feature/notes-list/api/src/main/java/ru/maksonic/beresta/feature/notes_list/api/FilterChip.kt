package ru.maksonic.beresta.feature.notes_list.api

/**
 * @Author maksonic on 18.01.2023
 */
data class FilterChip(
    val id: Long,
    val title: String,
    val isSelected: Boolean,
) {
    companion object {
        val Preview = FilterChip(0, "Preview chip", false)
    }
}