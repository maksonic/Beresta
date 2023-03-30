package ru.maksonic.beresta.feature.folders_list.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.folders_list.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.folders_list.core.presentation.dialog.ui.FolderCreationDialogContent
import ru.maksonic.beresta.feature.folders_list.core.presentation.widget.chips_list.FilterChipsWidgetContent
import ru.maksonic.beresta.feature.language_selector.api.provider.text

/**
 * @Author maksonic on 30.03.2023
 */
class FoldersListUiCore : FoldersListApi.Ui {
    override val dialogVisibilitySharedState = object : SharedUiState<Boolean>(false) {}

    @Composable
    override fun FolderCreationDialog() {

        FolderCreationDialogContent(
            updateDialogVisibility = { state -> dialogVisibilitySharedState.updateState(state) }
        )
    }

    @Composable
    override fun FolderChipsWidget(
        chipsCollection: FilterChipUi.Collection,
        isVisibleFirstNote: () -> Boolean,
        onChipFilterClicked: (id: Long) -> Unit,
        modifier: Modifier
    ) {

        FilterChipsWidgetContent(
            chipsCollection = chipsCollection.copy(
                data = chipsCollection.data.map {
                    applyInitialChipTitleForLanguage(chip = it)
                }
            ),
            isVisibleFirstNote = isVisibleFirstNote,
            onChipFilterClicked = onChipFilterClicked,
            onAddNewFilterFolderClicked = { dialogVisibilitySharedState.updateState(true) },
            modifier = modifier
        )
    }
}

@Composable
private fun applyInitialChipTitleForLanguage(chip: FilterChipUi): FilterChipUi {
    val title = if (chip.id == 0L) text.shared.titlePinnedChipFilter else chip.title
    return chip.copy(title = title)
}