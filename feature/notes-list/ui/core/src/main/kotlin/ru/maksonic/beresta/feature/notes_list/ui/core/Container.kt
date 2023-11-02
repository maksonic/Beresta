package ru.maksonic.beresta.feature.notes_list.ui.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import ru.maksonic.beresta.common.core.ui.sorting.FilterDataSorter
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.placeholder.PlaceholderEmptyState
import ru.maksonic.beresta.common.ui_kit.placeholder.PlaceholderNotesLoading
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.noteUiCardState
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesListUiState
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 01.10.2023
 */
@Composable
internal fun Container(
    isPrimary: Boolean,
    state: NotesListUiState,
    sorter: State<FilterDataSorter<NoteUi>>,
    gridCells: Int,
    onNoteClicked: (id: Long) -> Unit,
    onNoteLongClicked: (id: Long) -> Unit,
    updateScrollUpValue: (Boolean) -> Unit,
    updateCanScrollBackwardValue: (Boolean) -> Unit,
    chipsRowOffset: State<Float>,
    updateChipsOffset: (Float) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier,
    loadingModifier: Modifier,
    emptyListPlaceholder: @Composable () -> Unit,
    cardBackground: @Composable (wallpaper: BaseWallpaper<Color>) -> Unit
) {
    Box {
        if (state.state.isLoading) {
            PlaceholderNotesLoading(gridCells, noteUiCardState.shape.dp, loadingModifier)
        }
        if (state.state.successAfterLoading) {
            if (sorter.value.isEmptyList) {
                emptyListPlaceholder()
            } else {
                Content(
                    isPrimary = isPrimary,
                    sorter = sorter,
                    gridCells = gridCells,
                    onNoteClicked = onNoteClicked,
                    onNoteLongClicked = onNoteLongClicked,
                    updateScrollUpValue = updateScrollUpValue,
                    updateCanScrollBackwardValue = updateCanScrollBackwardValue,
                    chipsOffset = chipsRowOffset,
                    updateChipsOffset = updateChipsOffset,
                    contentPadding = contentPadding,
                    modifier = modifier,
                    cardBackground = cardBackground
                )
            }
        }

        if (isPrimary) {
            AnimateFadeInOut(!state.state.isLoading && sorter.value.isEmptyList) {
                PlaceholderEmptyState(
                    painter = painterResource(id = Theme.image.imageEmptyNotes),
                    message = text.shared.hintNoNotes
                )
            }
        }
    }
}