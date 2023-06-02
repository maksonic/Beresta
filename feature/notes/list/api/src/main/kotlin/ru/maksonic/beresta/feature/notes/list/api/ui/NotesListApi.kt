package ru.maksonic.beresta.feature.notes.list.api.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter

/**
 * @Author maksonic on 21.02.2023
 */
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
            currentAppLang: AppLanguage,
            modifier: Modifier
        )

        @Composable
        fun EmptyListWidget()

        @Composable
        fun NotesLoaderWidget(modifier: Modifier)
    }
}