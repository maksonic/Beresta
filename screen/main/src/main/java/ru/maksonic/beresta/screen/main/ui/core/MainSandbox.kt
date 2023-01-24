package ru.maksonic.beresta.screen.main.ui.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature
import ru.maksonic.beresta.feature.notes_list.api.NotesListFeature
import ru.maksonic.beresta.feature.tasks_list.api.TasksListFeature

/**
 * @Author maksonic on 16.01.2023
 */
private typealias UpdateResult = UpdatedModel<Screen.Model, Set<Screen.Cmd>, Set<Screen.Eff>>

class MainSandbox(
    bottomPanelActionsMainProgram: BottomPanelActionsMainProgram,
    notesListFeature: NotesListFeature,
    tasksListFeature: TasksListFeature,
    bottomPanelFeature: BottomPanelFeature
) : Sandbox<Screen.Model, Screen.Msg, Screen.Cmd, Screen.Eff>(
    initialModel = Screen.Model(
        notesListFeature = notesListFeature,
        tasksListFeature = tasksListFeature,
        bottomPanelFeature = bottomPanelFeature
    ),
    initialCmd = setOf(Screen.Cmd.ListenBottomPanelActions),
    subscriptions = listOf(bottomPanelActionsMainProgram)
) {
    override fun update(msg: Screen.Msg, model: Screen.Model): UpdateResult = when (msg) {
        is Screen.Msg.Inner.FetchNavEntry -> UpdatedModel(model.copy(entry = msg.from))
        is Screen.Msg.Inner.SetTopBarVisibility -> setTopBarVisibility(model, msg)
        is Screen.Msg.Inner.SetBottomVisibility -> setBottomBarVisibility(model, msg)
        is Screen.Msg.Inner.SetColoredTopBar -> setColoredTopBar(model, msg)
        is Screen.Msg.Ui.OnSettingsClicked -> onSettingsClicked(model)
        is Screen.Msg.Ui.OnShareSelectedNotes -> onShareSelectedNotesClicked(model)
        is Screen.Msg.Ui.OnTrashClicked -> onTrashClicked(model)
        is Screen.Msg.Ui.OnSearchClicked -> onSearchClicked(model)
    }


    private fun setTopBarVisibility(
        model: Screen.Model,
        msg: Screen.Msg.Inner.SetTopBarVisibility
    ): UpdateResult =
        UpdatedModel(model.copy(isVisibleTopBar = msg.value))

    private fun setBottomBarVisibility(
        model: Screen.Model,
        msg: Screen.Msg.Inner.SetBottomVisibility
    ): UpdateResult =
        UpdatedModel(model.copy(isVisibleBottomBar = msg.value))

    private fun setColoredTopBar(
        model: Screen.Model,
        msg: Screen.Msg.Inner.SetColoredTopBar
    ): UpdateResult =
        UpdatedModel(model.copy(isColoredTopBar = msg.value))

    private fun onSettingsClicked(model: Screen.Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Screen.Eff.NavigateToSettings))

    private fun onShareSelectedNotesClicked(model: Screen.Model): UpdateResult = UpdatedModel(model)

    private fun onTrashClicked(model: Screen.Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Screen.Eff.NavigateToTrash))

    private fun onSearchClicked(model: Screen.Model): UpdateResult = UpdatedModel(model)
}