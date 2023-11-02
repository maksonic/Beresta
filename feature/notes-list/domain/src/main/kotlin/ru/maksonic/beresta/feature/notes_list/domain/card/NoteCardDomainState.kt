package ru.maksonic.beresta.feature.notes_list.domain.card

/**
 * @Author maksonic on 13.10.2023
 */
enum class NoteCardShapeDomain { ROUNDED, SQUARED }
enum class NoteCardElevationDomain { ENABLED, DISABLED }

data class NoteCardDomainState(
    val shape: NoteCardShapeDomain,
    val elevation: NoteCardElevationDomain,
    val maxTitleLines: Int,
    val maxMessageLines: Int,
    val isVisibleColorMarker: Boolean,
    val isVisibleWallpaper: Boolean
)