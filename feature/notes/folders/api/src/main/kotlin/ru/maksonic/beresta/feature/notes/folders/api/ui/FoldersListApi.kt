package ru.maksonic.beresta.feature.notes.folders.api.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.navigation.router.router.NotesFoldersScreenRouter

/**
 * @Author maksonic on 30.03.2023
 */
interface FoldersListApi {

    interface Ui {
        val sharedUiState: SharedUiState<FoldersSharedUiState>

        @Composable
        fun Screen(router: NotesFoldersScreenRouter)

        @Composable
        fun ChipsWidget(
            chips: NoteFolderUi.Collection,
            onChipClicked: (id: Long) -> Unit,
            currentSelectedChipId: Long,
            modifier: Modifier
        )

        @Composable
        fun FolderCreationDialog()
    }
}