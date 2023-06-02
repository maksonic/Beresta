package ru.maksonic.beresta.screen.trash_list.folders.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 30.05.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class FoldersTrashSandbox(program: FoldersTrashProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchRemovedData),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedRemovedNotesResult -> fetchedDataResult(model, msg)
        is Msg.Inner.FetchedError -> UpdatedModel(model)
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnTrashedFoldersBtnClicked -> onTrashedFoldersBtnClicked(model)
        is Msg.Ui.OnNoteClicked -> onNoteClicked(model, msg)
        is Msg.Ui.OnNoteLongClicked -> onNoteLongClicked(model, msg)
        is Msg.Ui.OnSelectAllNotesClicked -> onSelectAllNotesClicked(model)
        is Msg.Ui.CancelSelectionState -> onCancelSelectionClicked(model)
    }

    private fun fetchedDataResult(
        model: Model,
        msg: Msg.Inner.FetchedRemovedNotesResult
    ): UpdateResult = UpdatedModel(
        model.copy(
            base = model.base.copy(
                isLoading = false,
                isSuccessLoading = true
            ),
            folders = model.folders.copy(msg.folders)
        )
    )

    private fun onTopBarBackPressed(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.NavigateBack))

    private fun onTrashedFoldersBtnClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(isSelectionState = false, selectedFolders = emptySet()),
        effects = setOf(Eff.NavigateToTrashedFoldersList)
    )

    private fun onNoteClicked(model: Model, msg: Msg.Ui.OnNoteClicked): UpdateResult =
        if (model.isSelectionState)
            baseOnNoteAction(model, msg.id)
        else
            UpdatedModel(model)


    private fun onNoteLongClicked(model: Model, msg: Msg.Ui.OnNoteLongClicked): UpdateResult =
        baseOnNoteAction(model, msg.id)

    private fun baseOnNoteAction(model: Model, noteId: Long): UpdateResult {
        val selected = model.selectedFolders.toMutableSet().also { list ->
            model.folders.data.forEach { note ->
                if (noteId == note.id) {
                    if (list.contains(note)) list.remove(note) else list.add(note)
                }
            }
        }.toSet()

        return UpdatedModel(
            model.copy(
                selectedFolders = selected,
                isSelectionState = true,
            )
        )
    }

    private fun onSelectAllNotesClicked(model: Model): UpdateResult {
        val selected = model.selectedFolders.toMutableSet().also { list ->
            if (list.containsAll(model.folders.data)) list.clear()
            else list.addAll(model.folders.data)
        }.toSet()

        return UpdatedModel(model.copy(selectedFolders = selected))
    }

    private fun onCancelSelectionClicked(model: Model): UpdateResult =
        UpdatedModel(model.copy(selectedFolders = emptySet(), isSelectionState = false))
}