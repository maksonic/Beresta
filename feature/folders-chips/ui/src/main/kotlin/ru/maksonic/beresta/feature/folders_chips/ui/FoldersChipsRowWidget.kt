package ru.maksonic.beresta.feature.folders_chips.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.api.ui.updateId

/**
 * @Author maksonic on 03.07.2023
 */
class FoldersChipsRowWidget(
    override val currentSelectedId: SharedUiState<Long>
) : FoldersApi.Ui.ChipsRow {

    @Composable
    override fun Widget(
        isLoading: State<Boolean>,
        isColoredBackground: State<Boolean>,
        chips: FolderUi.Collection,
        chipsRowOffsetHeightPx: State<Float>,
        onAddNewChipClicked: () -> Unit,
    ) {

        Container(
            isLoading = isLoading,
            isColoredBackground = isColoredBackground,
            chips = chips,
            chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
            onAddNewChipClicked = onAddNewChipClicked,
            updateCurrentSelectedFolder = { currentSelectedId.updateId(it) }
        )
    }
}