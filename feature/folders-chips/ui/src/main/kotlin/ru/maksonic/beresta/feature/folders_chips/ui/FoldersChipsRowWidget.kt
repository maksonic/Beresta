package ru.maksonic.beresta.feature.folders_chips.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi

/**
 * @Author maksonic on 03.07.2023
 */
class FoldersChipsRowWidget(
    override val currentSelectedChipId: MutableState<Long>) : FoldersApi.Ui.ChipsRow {

    @Composable
    override fun Widget(
        isLoading: State<Boolean>,
        chips: FolderUi.Collection,
        chipsRowOffsetHeightPx: MutableState<Float>,
        onAddNewChipClicked: () -> Unit,
    ) {
        Container(
            isLoading = isLoading,
            chips = chips,
            currentSelectedId = currentSelectedChipId,
            chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
            onAddNewChipClicked = onAddNewChipClicked,
        )
    }
}