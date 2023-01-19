package ru.maksonic.beresta.feature.notes_list.ui.core

import android.util.Log
import kotlinx.coroutines.flow.update
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanel
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature

/**
 * @Author maksonic on 19.01.2023
 */
class BottomPanelActionProgram(
    private val feature: BottomPanelFeature
) : ElmProgram<Feature.Msg, Feature.Cmd> {
    override suspend fun executeProgram(cmd: Feature.Cmd, consumer: (Feature.Msg) -> Unit) {
        when (cmd) {
            is Feature.Cmd.ListenBottomPanelActions -> executePanelActions(consumer)
            else -> {}
        }
    }

    private suspend fun executePanelActions(consumer: (Feature.Msg) -> Unit) {
        feature.state.state.collect { panel ->
            when (panel.action) {
                BottomPanel.Action.NOTHING -> {}
                BottomPanel.Action.CANCEL -> {
                    consumer(Feature.Msg.Ui.CancelNotesSelection)
                    feature.state.mutableState.update { it.copy(action = BottomPanel.Action.NOTHING) }
                }
                BottomPanel.Action.SELECT_ALL -> {
                    Log.e("AAA", "SELECT ALL CLICKED")
                    feature.state.mutableState.update { it.copy(action = BottomPanel.Action.NOTHING) }
                    consumer(Feature.Msg.Ui.SelectAllNotes)
                }
            }
        }
    }
}