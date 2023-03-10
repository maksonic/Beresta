package ru.maksonic.beresta.screen.main.presentation.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 16.01.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class MainSandbox(
    mainProgram: MainProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model(),
    initialCmd = setOf(Cmd.RunFetchingNotesCollection),
    subscriptions = listOf(mainProgram)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedNotesCollection -> fetchedNotes(model, msg)
        is Msg.Inner.FetchedError -> fetchedError(model, msg)
        is Msg.Ui.CreateNewNote -> onAddNoteClicked(model)
        is Msg.Inner.SetTopBarVisibility -> setTopBarVisibility(model, msg)
        is Msg.Inner.SetBottomVisibility -> setBottomBarVisibility(model, msg)
        is Msg.Inner.SetColoredTopBar -> setColoredTopBar(model, msg)
        is Msg.Ui.OnShareSelectedNotes -> onShareSelectedNotesClicked(model)
        is Msg.Ui.OnSettingsClicked -> onSettingsClicked(model)
        is Msg.Ui.OnTrashClicked -> onTrashClicked(model)
        is Msg.Ui.OnOpenFoldersClicked -> UpdatedModel(model)
        is Msg.Ui.OnSortNotesByClicked -> UpdatedModel(model)

    }

    private fun fetchedNotes(model: Model, msg: Msg.Inner.FetchedNotesCollection): UpdateResult =
        UpdatedModel(
            model.copy(
                base = model.base.copy(isLoading = false, isSuccessLoading = true, isError = false),
                notes = msg.data
            )
        )

    private fun fetchedError(model: Model, msg: Msg.Inner.FetchedError): UpdateResult =
        UpdatedModel(
            model.copy(
                base = model.base.copy(
                    isLoading = false,
                    isSuccessLoading = false,
                    isError = true,
                    errorMsg = msg.message
                )
            )
        )

    private fun onAddNoteClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateToAddNewNote))

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

}