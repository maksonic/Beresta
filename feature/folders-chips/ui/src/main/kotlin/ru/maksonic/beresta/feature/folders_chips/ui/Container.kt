package ru.maksonic.beresta.feature.folders_chips.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import ru.maksonic.beresta.elm.core.ElmBaseModel
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.ui.widget.ChipsError
import ru.maksonic.beresta.feature.folders_chips.ui.widget.ChipsPlaceholder
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.surface.SurfacePro
import kotlin.math.roundToInt

/**
 * @Author maksonic on 04.07.2023
 */
@Composable
internal fun Container(
    state: ElmBaseModel,
    isColoredBackground: State<Boolean>,
    chips: FolderUi.Collection,
    chipsRowOffsetHeightPx: State<Float>,
    onAddNewChipClicked: () -> Unit,
    updateCurrentSelectedFolder: (Long) -> Unit,
    onRetryFetchClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val tonal = animateDp(if (isColoredBackground.value) Theme.tonal.Level2 else Theme.tonal.Level0)

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
        SurfacePro(
            tonalElevation = tonal.value,
            modifier = modifier
                .statusBarsPadding()
                .padding(top = Theme.widgetSize.topBarSmallHeight)
                .height(Theme.widgetSize.noteChipsContainerHeight)
                .offset { IntOffset(x = 0, y = chipsRowOffsetHeightPx.value.roundToInt()) }
        ) { resultColor ->
            val resultColorState = rememberUpdatedState(resultColor)

            when {
                state.isLoading -> ChipsPlaceholder()
                state.successAfterLoading -> {
                    Content(
                        chips = chips,
                        onAddNewChipClicked = onAddNewChipClicked,
                        updateCurrentSelectedFolder = updateCurrentSelectedFolder,
                        addNewChipBackgroundColor = resultColorState
                    )
                }

                state.failAfterLoading -> {
                    ChipsError(
                        onRetryFetchClicked = onRetryFetchClicked,
                        onAddNewChipClicked = onAddNewChipClicked,
                        backgroundColor = resultColorState
                    )
                }
            }
        }
    }
}