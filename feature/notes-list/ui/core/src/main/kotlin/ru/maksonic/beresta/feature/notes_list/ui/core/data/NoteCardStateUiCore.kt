package ru.maksonic.beresta.feature.notes_list.ui.core.data

import androidx.compose.runtime.mutableStateOf
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardElevationUi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardShapeUi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardUiState
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NotesCardUiApi
import ru.maksonic.beresta.platform.core.ui.update

/**
 * @Author maksonic on 21.10.2023
 */
private const val INITIAL_TITLE_LINES_COUNT = 1
private const val INITIAL_MESSAGE_LINES_COUNT = 2

class NoteCardStateUiCore : NotesCardUiApi.CardState {
    override val sharedState = mutableStateOf(NoteCardUiState.Initial)

    override fun update(state: NoteCardUiState) = sharedState.update(state)

    override fun updateShape(shape: NoteCardShapeUi) =
        sharedState.update(sharedState.value.copy(shape = shape))

    override fun updateElevation(elevation: NoteCardElevationUi) =
        sharedState.update(sharedState.value.copy(elevation = elevation))

    override fun updateWallpaperVisibility(isVisible: Boolean) =
        sharedState.update(sharedState.value.copy(isVisibleWallpaper = isVisible))

    override fun updateMaxTitleLines(count: Int) =
        sharedState.update(sharedState.value.copy(maxTitleLines = count))

    override fun updateMaxMessageLines(count: Int) =
        sharedState.update(sharedState.value.copy(maxMessageLines = count))

    override fun updateColorMarkerVisibility(isVisible: Boolean) =
        sharedState.update(sharedState.value.copy(isVisibleColorMarker = isVisible))

    override fun resetNoteCardLinesByDefault() = sharedState.update(
        sharedState.value.copy(
            maxTitleLines = INITIAL_TITLE_LINES_COUNT,
            maxMessageLines = INITIAL_MESSAGE_LINES_COUNT
        )
    )
}