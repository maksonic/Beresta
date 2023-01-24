package ru.maksonic.beresta.screen.main.ui.core

import kotlinx.coroutines.flow.update
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanel
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature

/**
 * @Author maksonic on 23.01.2023
 */
class BottomPanelActionsMainProgram(
    private val feature: BottomPanelFeature
) : ElmProgram<Screen.Msg, Screen.Cmd> {
    override suspend fun executeProgram(cmd: Screen.Cmd, consumer: (Screen.Msg) -> Unit) {
        when (cmd) {
            is Screen.Cmd.ListenBottomPanelActions -> executePanelActions(consumer)
            else -> {}
        }
    }

    private fun executePanelActions(consumer: (Screen.Msg) -> Unit) {
        val bottomPanelActions = mapOf(
            BottomPanel.Action.Notes.Idle.TRASH to { consumer(Screen.Msg.Ui.OnTrashClicked) },
            BottomPanel.Action.Notes.Idle.FOLDERS to {
            },
            BottomPanel.Action.Notes.Idle.SEARCH to { consumer(Screen.Msg.Ui.OnSearchClicked) },
            BottomPanel.Action.Notes.Idle.ADD_NOTE to {
            },
            BottomPanel.Action.Notes.Idle.FAVORITES to {

            },
            BottomPanel.Action.Notes.Idle.SORT_BY to {
            },
            BottomPanel.Action.Notes.Idle.SWITCH_VIEW_STATE to {
            }
        )

        feature.state.mutableState.update { panelState ->
            panelState.copy(idleActions = bottomPanelActions)
        }
    }
}