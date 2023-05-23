package ru.maksonic.beresta.feature.notes.list.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel

/**
 * @Author maksonic on 24.04.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class NotesListSandbox(program: NotesListProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchNotesList, Cmd.FetchCurrentLangForNotesDatestamp),
    subscriptions = listOf(program)
) {
    companion object {
        private const val STICKY_START_FOLDER_ID = 1L
        private const val STICKY_END_FOLDER_ID = 2L
        private const val DEFAULT_NOTE_FOLDER_ID = 2L
    }

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedResultData -> fetchedResultData(model, msg)
        is Msg.Inner.FetchedResultError -> fetchedResultError(model, msg)
        is Msg.Ui.OnNoteClicked -> onNoteClicked(model, msg)
        is Msg.Ui.OnNoteLongClicked -> onNoteLongClicked(model, msg)
        is Msg.Ui.OnBarMoveToTrashClicked -> onBarMoveSelectedToTrashClicked(model)
        is Msg.Ui.OnBarMoveToSecureFolderClicked -> onBarMoveNotesToSecureFolderClicked(model)
        is Msg.Ui.OnBarPinToTopOfListClicked -> onBarPinSelectedClicked(model)
        is Msg.Ui.OnBarReplaceToFolderClicked -> onBarReplaceToFolderClicked(model)
        is Msg.Ui.OnSelectAllNotesClicked -> onSelectAllNotesClicked(model)
        is Msg.Ui.OnShareSelectedClicked -> onShareNotesClicked(model)
        is Msg.Ui.CancelSelectionState -> onCancelSelectionClicked(model)
        is Msg.Ui.OnChangeGridCountClicked -> onChangeGridCountClicked(model)
        is Msg.Ui.OnSnackUndoRemoveNotesClicked -> onSnackBarUndoRemoveClicked(model)
        is Msg.Inner.ShowRemovedNotesSnackBar -> showRemoveNotesSnackBar(model)
        is Msg.Inner.HideRemovedNotesSnackBar -> hideRemoveNotesSnackBar(model)
        is Msg.Inner.FetchedCurrentAppLang -> applyLangToNotesDatestamp(model, msg)
        is Msg.Inner.FetchedCurrentFolderIdByPassedState -> {
            fetchedCurrentFolderIdByPassedState(model, msg)
        }
    }

    private fun fetchedResultData(model: Model, msg: Msg.Inner.FetchedResultData): UpdateResult {
        return UpdatedModel(
            model.copy(
                base = model.base.copy(isLoading = false, isSuccessLoading = true),
                notes = msg.notes
            )
        )
    }

    private fun fetchedResultError(model: Model, msg: Msg.Inner.FetchedResultError): UpdateResult =
        UpdatedModel(
            model.copy(
                base = model.base.copy(
                    isLoading = false,
                    isSuccessLoading = false,
                    isErrorLoading = true
                ),
                errorMsg = msg.errorMsg
            )
        )

    private fun onNoteClicked(model: Model, msg: Msg.Ui.OnNoteClicked): UpdateResult =
        if (model.isSelectionState)
            baseOnNoteAction(model, msg.id)
        else
            UpdatedModel(model, effects = setOf(Eff.NavigateToEditNote(msg.id)))


    private fun onNoteLongClicked(model: Model, msg: Msg.Ui.OnNoteLongClicked): UpdateResult =
        baseOnNoteAction(model, msg.id)

    private fun baseOnNoteAction(model: Model, noteId: Long): UpdateResult {
        val selected = model.selectedNotes.toMutableSet().also { list ->
            model.notes.data.forEach { note ->
                if (noteId == note.id) {
                    if (list.contains(note)) list.remove(note) else list.add(note)
                }
            }
        }.toSet()
        val isShowUnpinButton = !selected.map { it.isPinned }.contains(false)

        return UpdatedModel(
            model.copy(
                selectedNotes = selected,
                isSelectionState = true,
                isShowUnpinMainBarIcon = isShowUnpinButton
            ),
            effects = setOf(
                Eff.UpdateSharedUiIsSelectedState(true),
                Eff.UpdateSharedUiIsEnabledBottomBarState(selected.isNotEmpty())
            )
        )
    }

    private fun onBarMoveSelectedToTrashClicked(model: Model): UpdateResult {
        val removed = model.removedNotes.toMutableSet().also { list ->
            val isTrashItems = model.selectedNotes.map { note -> note.copy(isMovedToTrash = true) }
            list.addAll(isTrashItems)
        }.toSet()
        val notes = model.notes.copy(data = model.notes.data.filterNot { note ->
            removed.any { it.id == note.id }
        })

        return UpdatedModel(
            model = model.copy(
                notes = notes,
                selectedNotes = emptySet(),
                removedNotes = removed,
                isSelectionState = false,
            ),
            commands = setOf(Cmd.RemoveSelected(removed.toList())),
            effects = setOf(Eff.UpdateSharedUiIsSelectedState(false))
        )
    }

    private fun onBarMoveNotesToSecureFolderClicked(model: Model): UpdateResult =
        UpdatedModel(model)

    private fun onBarPinSelectedClicked(model: Model): UpdateResult {
        return UpdatedModel(
            model = model.copy(selectedNotes = emptySet()),
            commands = setOf(Cmd.UpdatePinnedNotesInCache(model.selectedNotes))
        )
    }

    private fun onBarReplaceToFolderClicked(model: Model): UpdateResult =
        UpdatedModel(
            model,
            effects = setOf(
                Eff.UpdatePassedNotesSharedState(model.selectedNotes.toList()),
                Eff.NavigateToFoldersWithMovingState(model.currentSelectedFolderId)
            )
        )

    private fun onSelectAllNotesClicked(model: Model): UpdateResult {
        val filteredNotes = model.notes.data.filter { note ->
            when (model.currentSelectedFolderId) {
                STICKY_START_FOLDER_ID -> note.id == note.id
                STICKY_END_FOLDER_ID -> note.folderId == DEFAULT_NOTE_FOLDER_ID
                else -> note.folderId == model.currentSelectedFolderId
            }
        }

        val selectedNotes = model.selectedNotes.toMutableSet().also { list ->
            if (model.currentSelectedFolderId == STICKY_START_FOLDER_ID) {
                if (list.containsAll(model.notes.data)) list.clear()
                else list.addAll(model.notes.data)
            } else {
                if (list.containsAll(filteredNotes)) list.removeAll(filteredNotes.toSet())
                else list.addAll(filteredNotes)
            }
        }.toSet()

        val isShowUnpinButton = !selectedNotes.map { it.isPinned }.contains(false)

        return UpdatedModel(
            model.copy(
                selectedNotes = selectedNotes,
                isShowUnpinMainBarIcon = isShowUnpinButton
            ),
            effects = setOf(Eff.UpdateSharedUiIsEnabledBottomBarState(selectedNotes.isNotEmpty()))
        )
    }

    private fun onShareNotesClicked(model: Model): UpdateResult = UpdatedModel(model)

    private fun onCancelSelectionClicked(model: Model): UpdateResult =
        UpdatedModel(
            model = model.copy(selectedNotes = emptySet(), isSelectionState = false),
            effects = setOf(Eff.UpdateSharedUiIsSelectedState(false))
        )

    private fun onChangeGridCountClicked(model: Model): UpdateResult =
        UpdatedModel(model.copy(gridCount = if (model.gridCount == 1) 2 else 1))


    private fun showRemoveNotesSnackBar(model: Model): UpdateResult =
        UpdatedModel(model.copy(isVisibleRemovedSnackBar = true))

    private fun hideRemoveNotesSnackBar(model: Model): UpdateResult =
        UpdatedModel(model.copy(isVisibleRemovedSnackBar = false, removedNotes = emptySet()))

    private fun onSnackBarUndoRemoveClicked(model: Model): UpdateResult {
        val removed = model.notes.copy(data = model.notes.data.toMutableList().also { notes ->
            notes.addAll(model.removedNotes.map { it.copy(isMovedToTrash = false) })
        }.toList())

        return UpdatedModel(
            model = model.copy(notes = removed, removedNotes = emptySet()),
            commands = setOf(Cmd.UndoRemoveNotes(removed.data)),
        )
    }

    private fun applyLangToNotesDatestamp(
        model: Model,
        msg: Msg.Inner.FetchedCurrentAppLang
    ): UpdateResult = UpdatedModel(model.copy(currentAppLanguage = msg.language))

    private fun fetchedCurrentFolderIdByPassedState(
        model: Model,
        msg: Msg.Inner.FetchedCurrentFolderIdByPassedState
    ): UpdateResult =
        UpdatedModel(model.copy(currentSelectedFolderId = msg.id))
}