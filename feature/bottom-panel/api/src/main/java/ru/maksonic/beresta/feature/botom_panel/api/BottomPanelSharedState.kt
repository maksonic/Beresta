package ru.maksonic.beresta.feature.botom_panel.api

import ru.maksonic.beresta.core.MutableSharedState

/**
 * @Author maksonic on 19.01.2023
 */
data class PanelSharedState(
    val state: BottomPanel.State = BottomPanel.State.IDLE,
    val action: BottomPanel.Action = BottomPanel.Action.NOTHING
)

class BottomPanelSharedState : MutableSharedState<PanelSharedState>(PanelSharedState())