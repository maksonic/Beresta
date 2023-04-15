package ru.maksonic.beresta.feature.folders_list.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.folders_list.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.folders_list.api.ui.FoldersSharedUiState
import ru.maksonic.beresta.feature.folders_list.api.ui.updateDialogVisibility
import ru.maksonic.beresta.feature.folders_list.core.chips_list.FilterChipsWidgetContent
import ru.maksonic.beresta.feature.folders_list.core.dialog.ui.FolderCreationDialogContent
import ru.maksonic.beresta.feature.folders_list.core.screen.ui.FoldersScreenContent
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.navigation.router.router.FoldersScreenRouter

/**
 * @Author maksonic on 30.03.2023
 */
class FoldersListUiCore : FoldersListApi.Ui {
    override val sharedUiState = FoldersSharedUiState.Initial

    @Composable
    override fun FoldersListScreen(router: FoldersScreenRouter) {
        FoldersScreenContent(router = router)
    }

    @Composable
    override fun FolderCreationDialog() {
        FolderCreationDialogContent(sharedUiState = sharedUiState)
    }

    @Composable
    override fun FolderChipsWidget(
        chipsCollection: FilterChipUi.Collection,
        currentSelectedChipId: Long,
        isVisibleFirstNote: State<Boolean>,
        onChipFilterClicked: (id: Long) -> Unit,
        modifier: Modifier
    ) {

        FilterChipsWidgetContent(
            chipsCollection = chipsCollection.copy(
                data = chipsCollection.data.map { applyInitialChipTitleForLanguage(chip = it) }
            ),
            currentSelectedChipId = currentSelectedChipId,
            isVisibleFirstNote = isVisibleFirstNote,
            onChipFilterClicked = onChipFilterClicked,
            onAddNewFilterFolderClicked = { sharedUiState.updateDialogVisibility(true) },
            modifier = modifier
        )
    }
}

@Composable
internal fun applyInitialChipTitleForLanguage(chip: FilterChipUi): FilterChipUi {
    val title = if (chip.id == 0L) text.folders.titlePinnedChipFilter else chip.title
    return chip.copy(title = title)
}