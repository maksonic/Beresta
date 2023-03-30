package ru.maksonic.beresta.feature.folders_list.api.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 30.03.2023
 */
interface FoldersListApi {
    interface Ui {
        @Composable
        fun FolderCreationDialog()

        @Composable
        fun FolderChipsWidget(
            chipsCollection: FilterChipUi.Collection,
            isVisibleFirstNote: () -> Boolean,
            onChipFilterClicked: (id: Long) -> Unit,
            modifier: Modifier
        )
        val dialogVisibilitySharedState: SharedUiState<Boolean>
    }
}