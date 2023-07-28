package ru.maksonic.beresta.feature.sorting_sheet.api

/**
 * @Author maksonic on 06.07.2023
 */
enum class SortDataKey {
    NOTES, HIDDEN_NOTES, FOLDERS
}

val SortDataKey.isFolders get() = this == SortDataKey.FOLDERS