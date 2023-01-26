package ru.maksonic.beresta.screen.main.ui.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature
import ru.maksonic.beresta.feature.notes_list.api.feature.NotesListFeature
import ru.maksonic.beresta.feature.tasks_list.api.TasksListFeature

/**
 * @Author maksonic on 16.01.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class MainSandbox(
    bottomPanelActionsMainProgram: BottomPanelActionsMainProgram,
    notesListFeature: NotesListFeature,
    tasksListFeature: TasksListFeature,
    bottomPanelFeature: BottomPanelFeature
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model(
        notesListFeature = notesListFeature,
        tasksListFeature = tasksListFeature,
        bottomPanelFeature = bottomPanelFeature
    ),
    initialCmd = setOf(Cmd.ListenBottomPanelActions),
    subscriptions = listOf(bottomPanelActionsMainProgram)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchNavEntry -> UpdatedModel(model.copy(entry = msg.from))
        is Msg.Inner.SetTopBarVisibility -> setTopBarVisibility(model, msg)
        is Msg.Inner.SetBottomVisibility -> setBottomBarVisibility(model, msg)
        is Msg.Inner.SetColoredTopBar -> setColoredTopBar(model, msg)
        is Msg.Ui.OnSettingsClicked -> onSettingsClicked(model)
        is Msg.Ui.OnShareSelectedNotes -> onShareSelectedNotesClicked(model)
        is Msg.Ui.OnTrashClicked -> onTrashClicked(model)
        is Msg.Ui.OnSearchClicked -> onSearchClicked(model)
    }

    private fun setTopBarVisibility(
        model: Model,
        msg: Msg.Inner.SetTopBarVisibility
    ): UpdateResult =
        UpdatedModel(model.copy(isVisibleTopBar = msg.value))

    private fun setBottomBarVisibility(
        model: Model, msg: Msg.Inner.SetBottomVisibility
    ): UpdateResult =
        UpdatedModel(model.copy(isVisibleBottomBar = msg.value))

    private fun setColoredTopBar(model: Model, msg: Msg.Inner.SetColoredTopBar): UpdateResult =
        UpdatedModel(model.copy(isColoredTopBar = msg.value))

    private fun onSettingsClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateToSettings))

    private fun onShareSelectedNotesClicked(model: Model): UpdateResult = UpdatedModel(model)

    private fun onTrashClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateToTrash))

    private fun onSearchClicked(model: Model): UpdateResult = UpdatedModel(model)
}