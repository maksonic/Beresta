package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.screen.main.core.Model

/**
 * @Author maksonic on 03.07.2023
 */
@Composable
internal fun ChipsRow(
    api: FoldersApi.Ui.ChipsRow,
    isColoredBackground: State<Boolean>,
    model: State<Model>,
    chipsRowOffsetHeightPx: State<Float>,
    onAddNewChipClicked: () -> Unit,
) {
    val isLoading = rememberUpdatedState(model.value.chips.state.isLoading)

    api.Widget(
        isLoading = isLoading,
        isColoredBackground = isColoredBackground,
        chips = model.value.chips.collection,
        chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
        onAddNewChipClicked = onAddNewChipClicked,
    )
}