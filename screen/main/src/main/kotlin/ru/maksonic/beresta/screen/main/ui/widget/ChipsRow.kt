package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.screen.main.core.Model
import ru.maksonic.beresta.screen.main.core.Msg
import ru.maksonic.beresta.screen.main.ui.SendMessage

/**
 * @Author maksonic on 03.07.2023
 */
@Composable
internal fun ChipsRow(
    model: State<Model>,
    send: SendMessage,
    chipsRowUi: FoldersApi.ChipsRow.Ui,
    isColoredBackground: State<Boolean>,
    chipsRowOffsetHeightPx: State<Float>,
) {
    chipsRowUi.Widget(
        state = model.value.chips.state,
        isColoredBackground = isColoredBackground,
        chips = model.value.chips.collection,
        chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
        onAddNewChipClicked = { send(Msg.Ui.OnAddNewChipClicked) },
        onRetryFetchClicked = { send(Msg.Ui.OnRetryFetchChipsClicked)}
    )
}