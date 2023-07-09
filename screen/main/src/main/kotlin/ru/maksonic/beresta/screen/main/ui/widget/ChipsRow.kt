package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.screen.main.core.Model

/**
 * @Author maksonic on 03.07.2023
 */
@Composable
fun ChipsRow(
    api: FoldersApi.Ui.ChipsRow,
    model: State<Model>,
    chipsRowOffsetHeightPx: MutableState<Float>,
    onAddNewChipClicked: () -> Unit,
) {
    val isLoading = rememberUpdatedState(model.value.chips.state.isLoading)

    api.Widget(
        isLoading = isLoading,
        chips = model.value.chips.collection,
        chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
        onAddNewChipClicked = onAddNewChipClicked,
    )
}