package ru.maksonic.beresta.feature.folders_chips.ui.chips_row

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import ru.maksonic.beresta.core.ui.ext.update
import ru.maksonic.beresta.elm.core.ElmBaseModel
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi

/**
 * @Author maksonic on 03.07.2023
 */
class FoldersChipsRowWidget : FoldersApi.ChipsRow.Ui {
    override val currentSelectedId = mutableLongStateOf(1)
    override fun updateCurrent(id: Long) = currentSelectedId.update(id)

    @Composable
    override fun Widget(
        state: ElmBaseModel,
        isColoredBackground: State<Boolean>,
        chips: FolderUi.Collection,
        chipsRowOffsetHeightPx: State<Float>,
        onAddNewChipClicked: () -> Unit,
        onRetryFetchClicked: () -> Unit
    ) {

        Container(
            state = state,
            chips = chips,
            isColoredBackground = isColoredBackground,
            chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
            onAddNewChipClicked = onAddNewChipClicked,
            updateCurrentSelectedFolder = { updateCurrent(it) },
            onRetryFetchClicked = onRetryFetchClicked
        )
    }
}