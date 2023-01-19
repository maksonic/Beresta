package ru.maksonic.beresta.feature.botom_panel.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanel
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelSharedState
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 24.12.2022
 */
class BottomPanelWidget(private val panelSharedState: BottomPanelSharedState) : BottomPanelFeature {
    override val state: BottomPanelSharedState
        get() = panelSharedState


    data class BottomItem(val iconId: Int, val action: () -> Unit)

    @Composable
    override fun Widget(modifier: Modifier) {
        val panelState = state.state.collectAsState()

        Row(
            modifier
                .fillMaxWidth()
                .height(Theme.widgetSize.bottomPanelHeightIdle)
                .graphicsLayer {
                    shape = RoundedCornerShape(topStart = 16.dp.toPx(), topEnd = 16.dp.toPx())
                    clip = true
                }
                .background(tertiaryContainer)
                .noRippleClickable { },
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (panelState.value.state) {
                BottomPanel.State.IDLE -> IdlePanelState()
                BottomPanel.State.SELECTED -> SelectPanelState(state)
            }

        }
    }

 /*   @Preview
    @Composable
    private fun BottomPanelWidgetPreview() {
        BerestaTheme {
            Widget(Modifier)
        }
    }*/
}