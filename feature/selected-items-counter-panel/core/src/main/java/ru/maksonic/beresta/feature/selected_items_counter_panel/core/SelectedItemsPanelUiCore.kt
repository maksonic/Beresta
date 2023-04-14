package ru.maksonic.beresta.feature.selected_items_counter_panel.core

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.selected_items_counter_panel.api.SelectedItemsPanelUiApi
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Close
import ru.maksonic.beresta.ui.theme.icons.SelectAll
import ru.maksonic.beresta.ui.widget.button.IconAction

/**
 * @Author maksonic on 05.04.2023
 */
class SelectedItemsPanelUiCore : SelectedItemsPanelUiApi {

    @Composable
    override fun Widget(
        countValue: () -> Int,
        onSelectAction: () -> Unit,
        onCancelAction: () -> Unit
    ) {

        PanelWithSelectCounter(
            onSelectAction = onSelectAction,
            onCancelAction = onCancelAction,
            countValue = countValue
        )
    }

    @Composable
    private fun PanelWithSelectCounter(
        onSelectAction: () -> Unit,
        onCancelAction: () -> Unit,
        countValue: () -> Int,
        modifier: Modifier = Modifier
    ) {
        Row(
            modifier
                .height(Theme.widgetSize.bottomPanelHeightDefault)
                .padding(start = dp8, end = dp8)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconAction(
                icon = { AppIcon.Close },
                action = { onCancelAction() }
            )

            SelectedItemsCount(countNotes = { countValue() })

            IconAction(
                icon = { AppIcon.SelectAll },
                action = { onSelectAction() }
            )
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    private fun SelectedItemsCount(countNotes: () -> Int, modifier: Modifier = Modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = text.shared.hintSelectedItemsCount,
                style = TextDesign.bodyPrimary.copy(
                    color = onPrimary,
                    fontWeight = FontWeight.Bold
                ),
                modifier = modifier.padding(start = dp8, top = dp4, bottom = dp4)
            )

            AnimatedContent(
                targetState = countNotes(),
                transitionSpec = {
                    if (targetState > initialState) {
                        slideInVertically { height -> height } + fadeIn() with
                                slideOutVertically { height -> -height } + fadeOut()
                    } else {
                        slideInVertically { height -> -height } + fadeIn() with
                                slideOutVertically { height -> height } + fadeOut()
                    }.using(
                        SizeTransform(clip = false)
                    )
                }
            ) { targetCount ->
                Text(
                    text = " $targetCount",
                    style = TextDesign.bodyPrimary.copy(
                        color = onPrimary,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = modifier.padding(end = dp8)
                )
            }
        }
    }
}