package ru.maksonic.beresta.feature.notes_list.ui.api.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.common.core.ui.sorting.FilterDataSorter
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper

/**
 * @Author maksonic on 13.10.2023
 */
interface NotesListUiApi {
    @Composable
    fun ListPrimary(
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
        cardBackground: @Composable (wallpaper: BaseWallpaper<Color>) -> Unit
    )

    @Composable
    fun ListSecondary(
        state: NotesListUiState,
        sorter: State<FilterDataSorter<NoteUi>>,
        onNoteClicked: (id: Long) -> Unit,
        onNoteLongClicked: (id: Long) -> Unit,
        contentPadding: PaddingValues,
        modifier: Modifier,
        loadingModifier: Modifier,
        emptyListPlaceholder: @Composable () -> Unit,
        cardBackground: @Composable (wallpaper: BaseWallpaper<Color>) -> Unit
    )
}