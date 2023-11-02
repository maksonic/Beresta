package ru.maksonic.beresta.feature.sorting_sheet.domain

/**
 * @Author maksonic on 16.10.2023
 */
enum class SortDataKey {
    NOTES, HIDDEN_NOTES, FOLDERS
}

val SortDataKey.isFolders get() = this == SortDataKey.FOLDERS