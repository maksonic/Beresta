package ru.maksonic.beresta.feature.folders_list.api.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.navigation.router.router.FoldersScreenRouter

/**
 * @Author maksonic on 30.03.2023
 */
interface FoldersListApi {

    interface Ui {
        @Composable
        fun FoldersListScreen(router: FoldersScreenRouter)

        @Composable
        fun FolderCreationDialog()

        @Composable
        fun FolderChipsWidget(
            chipsCollection: FilterChipUi.Collection,
            currentSelectedChipId: Long,
            isVisibleFirstNote: State<Boolean>,
            onChipFilterClicked: (id: Long) -> Unit,
            modifier: Modifier
        )

        val sharedUiState: SharedUiState<FoldersSharedUiState>
    }
}