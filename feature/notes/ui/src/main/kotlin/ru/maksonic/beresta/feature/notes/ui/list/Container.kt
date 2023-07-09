package ru.maksonic.beresta.feature.notes.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import org.koin.compose.koinInject
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.NotesListUiState
import ru.maksonic.beresta.feature.notes.api.ui.SharedNotesUiState
import ru.maksonic.beresta.feature.notes.ui.NotesFilterUpdater
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.images.AddNotePlaceholder
import ru.maksonic.beresta.ui.theme.images.AppImage
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.placeholder.ScreenPlaceholder

/**
 * @Author maksonic on 03.07.2023
 */
@Composable
internal fun Container(
    state: NotesListUiState,
    currentFolderId: Long,
    sharedUiState: SharedUiState<SharedNotesUiState>,
    onNoteClicked: (id: Long) -> Unit,
    onNoteLongClicked: (id: Long) -> Unit,
    chipsRowOffsetHeightPx: MutableState<Float>,
    noteCard: NotesApi.Ui.Card = koinInject(),
) {
    val updater = NotesFilterUpdater(state.collection.data, currentFolderId)

    Box {
        Content(
            state = state,
            updater = updater,
            noteCard = noteCard,
            sharedUiState = sharedUiState,
            onNoteClicked = onNoteClicked,
            onNoteLongClicked = onNoteLongClicked,
            chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
        )
        AnimateFadeInOut(!state.state.isLoading && updater.isEmptyFilterList) {
            ScreenPlaceholder(AppImage.AddNotePlaceholder, text.shared.hintNoNotes)
        }
    }
}
