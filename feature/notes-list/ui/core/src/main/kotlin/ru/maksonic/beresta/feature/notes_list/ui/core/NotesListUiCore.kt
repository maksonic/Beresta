package ru.maksonic.beresta.feature.notes_list.ui.core

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.common.core.ui.sorting.FilterDataSorter
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.noteUiCardState
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesListUiApi
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesListUiState
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper

/**
 * @Author maksonic on 01.10.2023
 */
class NotesListUiCore : NotesListUiApi {
    @Composable
    override fun ListPrimary(
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
        cardBackground: @Composable (wallpaper: BaseWallpaper<Color>) -> Unit
    ) {
        Container(
            isPrimary = true,
            state = state,
            sorter = sorter,
            gridCells = gridCells,
            onNoteClicked = onNoteClicked,
            onNoteLongClicked = onNoteLongClicked,
            updateScrollUpValue = updateScrollUpValue,
            updateCanScrollBackwardValue = updateCanScrollBackwardValue,
            chipsRowOffset = chipsRowOffset,
            updateChipsOffset = updateChipsOffset,
            contentPadding = contentPadding,
            modifier = modifier,
            loadingModifier = loadingModifier,
            emptyListPlaceholder = {},
            cardBackground = cardBackground,
        )
    }

    @Composable
    override fun ListSecondary(
        state: NotesListUiState,
        sorter: State<FilterDataSorter<NoteUi>>,
        onNoteClicked: (id: Long) -> Unit,
        onNoteLongClicked: (id: Long) -> Unit,
        contentPadding: PaddingValues,
        modifier: Modifier,
        loadingModifier: Modifier,
        emptyListPlaceholder: @Composable () -> Unit,
        cardBackground: @Composable (wallpaper: BaseWallpaper<Color>) -> Unit
    ) {
        val emptyState = remember { mutableFloatStateOf(0f) }

        Container(
            isPrimary = false,
            state = state,
            sorter = sorter,
            gridCells = 1,
            onNoteClicked = onNoteClicked,
            onNoteLongClicked = onNoteLongClicked,
            updateScrollUpValue = {},
            updateCanScrollBackwardValue = { },
            chipsRowOffset = emptyState,
            updateChipsOffset = {},
            contentPadding = contentPadding,
            modifier = modifier,
            loadingModifier = loadingModifier,
            emptyListPlaceholder = emptyListPlaceholder,
            cardBackground = cardBackground
        )
    }
}