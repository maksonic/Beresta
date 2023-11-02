package ru.maksonic.beresta.feature.notes_list.domain.card

/**
 * @Author maksonic on 13.10.2023
 */
interface NoteCardInteractor {
    suspend fun setCardShape(shape: NoteCardShapeDomain)
    suspend fun setCardElevation(elevation: NoteCardElevationDomain)
    suspend fun setCardWallpaperVisibility(isVisible: Boolean)
    suspend fun setCardTitleMaxLines(value: Int)
    suspend fun setCardMessageMaxLines(value: Int)
    suspend fun setCardColorMarkerVisibility(isVisible: Boolean)
    suspend fun setByDefaultCardMaxLines()
}