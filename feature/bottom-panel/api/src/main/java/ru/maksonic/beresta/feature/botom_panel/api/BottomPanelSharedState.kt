package ru.maksonic.beresta.feature.botom_panel.api

import ru.maksonic.beresta.core.MutableSharedState

/**
 * @Author maksonic on 19.01.2023
 */
data class PanelSharedState(
    val state: BottomPanel.State = BottomPanel.State.IDLE,
    val idleActions: Map<BottomPanel.Action.Notes.Idle, () -> Unit> = emptyMap(),
    val selectActions: Map<BottomPanel.Action.Notes.Select, () -> Unit> = emptyMap(),
    val selectedCount: Int = 0,
    val isShowUnpinButton: Boolean = false
)

class BottomPanelSharedState : MutableSharedState<PanelSharedState>(PanelSharedState())