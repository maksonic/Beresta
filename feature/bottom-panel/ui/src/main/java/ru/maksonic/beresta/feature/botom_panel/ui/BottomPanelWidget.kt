package ru.maksonic.beresta.feature.botom_panel.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 24.12.2022
 */
class BottomPanelWidget : BottomPanelFeature {

    data class BottomItem(val iconId: Int, val action: () -> Unit)

    @Preview
    @Composable
    private fun BottomPanelWidgetPreview() {
        BerestaTheme {
            Content()
        }
    }

    @Composable
    override fun Widget() {
        Content()
    }

    @Composable
    fun Content(modifier: Modifier = Modifier) {
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
            IdlePanelState(panelHeight = Theme.widgetSize.bottomPanelHeightIdle)
        }
    }
}