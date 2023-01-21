package ru.maksonic.beresta.feature.notes_list.ui.core

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanel
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature
import ru.maksonic.beresta.feature.botom_panel.api.PanelSharedState

/**
 * @Author maksonic on 19.01.2023
 */
class BottomPanelActionProgram(
    private val feature: BottomPanelFeature
) : ElmProgram<Feature.Msg, Feature.Cmd> {
    private companion object {
        private const val BETWEEN_MESSAGE_DELAY = 50L
    }

    override suspend fun executeProgram(cmd: Feature.Cmd, consumer: (Feature.Msg) -> Unit) {
        when (cmd) {
            is Feature.Cmd.ListenBottomPanelActions -> {
                executePanelActions(consumer)
            }
            is Feature.Cmd.PassPinNotesStateToBottomPanel -> {
                passPinNotesStateToBottomPanel(cmd.isShowUnpinBtn)
            }
            else -> {}
        }
    }

    private suspend fun executePanelActions(consumer: (Feature.Msg) -> Unit) {
        feature.state.state.collect { panel ->
            when (panel.action) {
                BottomPanel.Action.NOTHING -> {}
                BottomPanel.Action.CANCEL -> {
                    consumer(Feature.Msg.Ui.CancelNotesSelection)
                    resetBottomPanelAction(feature.state.mutableState)
                }
                BottomPanel.Action.SELECT_ALL -> {
                    consumer(Feature.Msg.Ui.SelectAllNotes)
                    resetBottomPanelAction(feature.state.mutableState)
                }
                BottomPanel.Action.REMOVE -> {
                    consumer(Feature.Msg.Ui.RemoveSelectedItems)
                    resetBottomPanelAction(feature.state.mutableState)
                }
                BottomPanel.Action.REPLACE -> {
                    resetBottomPanelAction(feature.state.mutableState)

                }
                BottomPanel.Action.PIN -> {
                    consumer(Feature.Msg.Ui.PinSelectedNotes)
                    delay(BETWEEN_MESSAGE_DELAY)
                    consumer(Feature.Msg.Ui.CancelNotesSelection)
                    resetBottomPanelAction(feature.state.mutableState)

                }
                BottomPanel.Action.HIDE -> {
                    resetBottomPanelAction(feature.state.mutableState)

                }
            }
        }
    }

    private fun passPinNotesStateToBottomPanel(isPinItems: Boolean) {
        feature.state.update { panelSharedState ->
            panelSharedState.copy(isShowUnpinButton = isPinItems)
        }
    }

    private fun resetBottomPanelAction(panelState: MutableStateFlow<PanelSharedState>) {
        panelState.update { resetState ->
            resetState.copy(action = BottomPanel.Action.NOTHING)
        }
    }
}