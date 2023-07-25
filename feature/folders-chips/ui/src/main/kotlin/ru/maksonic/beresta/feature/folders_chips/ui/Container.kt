package ru.maksonic.beresta.feature.folders_chips.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.ui.widget.Placeholder
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi

/**
 * @Author maksonic on 04.07.2023
 */
@Composable
internal fun Container(
    isLoading: State<Boolean>,
    isColoredBackground: State<Boolean>,
    chips: FolderUi.Collection,
    chipsRowOffsetHeightPx: State<Float>,
    onAddNewChipClicked: () -> Unit,
    updateCurrentSelectedFolder: (Long) -> Unit,
    modifier: Modifier = Modifier,
    listSortUiState: SortingSheetApi.Ui = koinInject(),
) {
    Box(
        modifier
            .statusBarsPadding()
            .fillMaxSize(), contentAlignment = Alignment.TopCenter
    ) {
        if (isLoading.value) {
            Placeholder()
        } else {
            Content(
                isColoredBackground = isColoredBackground,
                chips = chips,
                chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
                onAddNewChipClicked = onAddNewChipClicked,
                updateCurrentSelectedFolder = updateCurrentSelectedFolder,
                listSortUiState = listSortUiState
            )
        }
    }
}