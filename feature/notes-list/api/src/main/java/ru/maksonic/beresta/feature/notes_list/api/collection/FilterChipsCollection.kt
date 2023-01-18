package ru.maksonic.beresta.feature.notes_list.api.collection

import androidx.compose.runtime.Immutable
import ru.maksonic.beresta.feature.notes_list.api.FilterChip

/**
 * @Author maksonic on 18.01.2023
 */
@Immutable
data class FilterChipsCollection(val chips: List<FilterChip>) {
    companion object {
        val Preview = FilterChipsCollection(
            listOf(
                FilterChip(0, "Все", true),
                FilterChip(1, "Просто папка", false),
                FilterChip(2, "Fake folder", false),
                FilterChip(3, "Some folder", false),
                FilterChip(4, "Favorites", false),
                FilterChip(5, "Test folder", false),
                FilterChip(6, "Best notes", false),
                FilterChip(7, "Maksonic", false),
            )
        )
    }
}