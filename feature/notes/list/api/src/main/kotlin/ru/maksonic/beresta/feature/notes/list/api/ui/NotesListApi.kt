package ru.maksonic.beresta.feature.notes.list.api.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.list.api.domain.DateFormatter
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter

/**
 * @Author maksonic on 21.02.2023
 */
typealias NotesSortPrefs = Triple<SortedNotes, Boolean, Int>

interface NotesListApi {

    interface Ui {
        val sharedUiState: SharedUiState<NotesListSharedUiState>

        @Composable
        fun ListWidget(router: MainScreenRouter)

        @Composable
        fun NoteListItem(
            isSelected: Boolean,
            note: NoteUi,
            onNoteClicked: (id: Long) -> Unit,
            onNoteLongClicked: (id: Long) -> Unit,
            isTrashPlacement: Boolean,
            currentAppLang: AppLanguage,
            formatter: DateFormatter,
            modifier: Modifier
        )

        @Composable
        fun EmptyListWidget()

        @Composable
        fun NotesLoaderWidget(modifier: Modifier)

        @Composable
        fun SortNotesModalSheet(
            currentSortItemSelected: MutableState<SortedNotes>,
            checkboxSortPinned: MutableState<Boolean>,
            onBtnSaveClicked: () -> Unit,
        )
    }

    interface SortedNotesState {
        val current: Flow<NotesSortPrefs>
        suspend fun setSortBy(sort: SortedNotes)
        suspend fun setSortPinned(isSorted: Boolean)
        suspend fun setGridView(count: Int)
    }
}