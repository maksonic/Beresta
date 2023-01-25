package ru.maksonic.beresta.feature.notes_list.ui.core

import kotlinx.coroutines.flow.update
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanel
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature

/**
 * @Author maksonic on 19.01.2023
 */
class BottomPanelActionsProgram(
    private val feature: BottomPanelFeature
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.ListenBottomPanelActions -> {
                executePanelActions(consumer)
            }
            is Cmd.PassPinNotesStateToBottomPanel -> {
                isShowUnpinButtonOnBottomPanel(cmd.isShowUnpinBtn)
            }
            else -> {}
        }
    }

    private fun executePanelActions(consumer: (Msg) -> Unit) {
        val bottomPanelActions = mapOf(
            BottomPanel.Action.Notes.Select.CANCEL to {
                consumer(Msg.Ui.CancelNotesSelection)
            },
            BottomPanel.Action.Notes.Select.SELECT_ALL to {
                consumer(Msg.Ui.SelectAllNotes)
            },
            BottomPanel.Action.Notes.Select.HIDE to { },
            BottomPanel.Action.Notes.Select.PIN to {
                consumer(Msg.Ui.PinSelectedNotes)
                consumer(Msg.Ui.CancelNotesSelection)
            },
            BottomPanel.Action.Notes.Select.REPLACE to { },
            BottomPanel.Action.Notes.Select.REMOVE to {
                consumer(Msg.Ui.RemoveSelectedItems)
            }
        )

        feature.state.mutableState.update { panelState ->
            panelState.copy(selectActions = bottomPanelActions)
        }
    }

    private fun isShowUnpinButtonOnBottomPanel(isPinItems: Boolean) {
        feature.state.update { panelSharedState ->
            panelSharedState.copy(isShowUnpinButton = isPinItems)
        }
    }
}