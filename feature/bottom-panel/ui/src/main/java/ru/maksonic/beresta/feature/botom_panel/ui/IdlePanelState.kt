package ru.maksonic.beresta.feature.botom_panel.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.feature.bottom_panel.ui.R
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.button.IconPrimaryAction

/**
 * @Author maksonic on 25.12.2022
 */
private val leftButtons = arrayOf(
    BottomPanelWidget.BottomItem(
        iconId = R.drawable.ic_trash,
        action = {},
    ),
    BottomPanelWidget.BottomItem(
        iconId = R.drawable.ic_folder_open,
        action = {}
    ),
    BottomPanelWidget.BottomItem(
        iconId = R.drawable.ic_search,
        action = {}
    ),
)
private val rightButtons = arrayOf(
    BottomPanelWidget.BottomItem(
        iconId = R.drawable.ic_favorite,
        action = {}
    ),
    BottomPanelWidget.BottomItem(
        iconId = R.drawable.ic_sorting,
        action = {}
    ),
    BottomPanelWidget.BottomItem(
        iconId = R.drawable.ic_grid_view,
        action = {}
    ),
)

@Preview(showBackground = true)
@Composable
private fun IdlePanelStatePreview() {
    BerestaTheme {
        IdlePanelState()
    }
}

@Composable
internal fun IdlePanelState(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        leftButtons.forEach { item ->
            IconAction(
                icon = painterResource(id = item.iconId),
                action = item.action,
                modifier = modifier.padding(start = dp4)
            )
        }

        Spacer(modifier.weight(1f))
        IconPrimaryAction(action = { })
        Spacer(modifier.weight(1f))

        rightButtons.forEach { item ->
            IconAction(
                icon = painterResource(id = item.iconId),
                action = item.action,
                modifier = modifier.padding(end = dp4)
            )
        }
    }
}