package ru.maksonic.beresta.feature.botom_panel.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.update
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanel
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelSharedState
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.R
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.IconAction

/**
 * @Author maksonic on 19.01.2023
 */
@Composable
fun SelectPanelState(state: BottomPanelSharedState, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth()) {
        Spacer(modifier.size(dp8))
        IconAction(icon = painterResource(id = R.drawable.ic_close), action = {
            state.mutableState.update { it.copy(action = BottomPanel.Action.CANCEL) }
        })
        Spacer(modifier.size(dp8))
        IconAction(icon = painterResource(id = R.drawable.ic_select_all), action = {
            state.mutableState.update { it.copy(action = BottomPanel.Action.SELECT_ALL) }
        })
        Spacer(modifier.weight(1f))
        IconAction(icon = painterResource(id = R.drawable.ic_lock), action = {})
        Spacer(modifier.size(dp8))
        IconAction(icon = painterResource(id = R.drawable.ic_pin), action = {})
        Spacer(modifier.size(dp8))
        IconAction(icon = painterResource(id = R.drawable.ic_move), action = {})
        Spacer(modifier.size(dp8))
        IconAction(icon = painterResource(id = R.drawable.ic_remove), action = {})
        Spacer(modifier.size(dp8))
    }
}


@Preview(showBackground = true)
@Composable
private fun SelectPanelStatePreview() {
    BerestaTheme {
        SelectPanelState(state = BottomPanelSharedState())
    }
}