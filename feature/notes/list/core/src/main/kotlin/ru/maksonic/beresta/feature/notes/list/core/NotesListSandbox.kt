package ru.maksonic.beresta.feature.notes.list.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.notes.list.core.program.NotesListDataProgram
import ru.maksonic.beresta.feature.notes.list.core.program.NotesListPreferencesProgram

/**
 * @Author maksonic on 24.04.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class NotesListSandbox(
    notesDataProgram: NotesListDataProgram,
    notesPrefsProgram: NotesListPreferencesProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(
        Cmd.FetchNotesList,
        Cmd.FetchCurrentLangForNotesDatestamp,
        Cmd.FetchNotesDatastorePrefs
    ),
    subscriptions = listOf(notesDataProgram, notesPrefsProgram)
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
        is Msg.Inner.HideRemovedNotesSnackBar -> hideRemoveNotesSnackBar(model)
        is Msg.Inner.FetchedCurrentAppLang -> applyLangToNotesDatestamp(model, msg)
        is Msg.Inner.FetchedNotesPrefs -> fetchedNotesPrefs(model, msg)
        is Msg.Inner.FetchedCurrentFolderIdByPassedState -> fetchedCurrentPassedFolderId(model, msg)
        is Msg.Inner.UpdatedNotesSortState -> updatedFetchedSortNotesValue(model, msg)
        is Msg.Inner.UpdatedCheckboxState -> updatedFetchedSortPinnedNotesValue(model, msg)
        is Msg.Inner.UpdatedGridViewState -> updatedFetchedNotesGridView(model, msg)
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

    private fun fetchedNotesPrefs(model: Model, msg: Msg.Inner.FetchedNotesPrefs): UpdateResult =
        UpdatedModel(
            model.copy(
                currentSortItemSelected = msg.sort,
                gridCount = msg.gridCount,
                isSortPinnedNotes = msg.isSortPinned
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
        val selectedList = model.selectedNotes.toMutableSet().also { list ->
            model.notes.data.forEach { note ->
                if (noteId == note.id) {
                    if (list.contains(note)) list.remove(note) else list.add(note)
                }
            }
        }.toSet()
        val isVisibleUnpinButton = selectedList.isNotEmpty().run {
            if (this) !selectedList.map { it.isPinned }.contains(false) else false
        }

        return UpdatedModel(
            model.copy(
                selectedNotes = selectedList,
                isSelectionState = true,
                isVisibleUnpinMainBarIcon = isVisibleUnpinButton
            ),
            effects = setOf(
                Eff.UpdateSharedUiIsSelectedState(true),
                Eff.UpdateSharedUiIsEnabledBottomBarState(selectedList.isNotEmpty())
            )
        )
    }

    private fun onBarMoveSelectedToTrashClicked(model: Model): UpdateResult {
        val notes = model.notes.copy(model.notes.data.filter { !model.selectedNotes.contains(it) })

        return UpdatedModel(
            model = model.copy(
                base = model.base.copy(isLoading = true),
                notes = notes,
                selectedNotes = emptySet(),
                removedNotes = model.selectedNotes,
                isSelectionState = false,
                isVisibleRemovedSnackBar = true
            ),
            commands = setOf(Cmd.RemoveSelected(model.selectedNotes.toList())),
            effects = setOf(Eff.UpdateSharedUiIsSelectedState(false))
        )
    }

    private fun onBarMoveNotesToSecureFolderClicked(model: Model): UpdateResult =
        UpdatedModel(model)

    private fun onBarPinSelectedClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(selectedNotes = emptySet()),
        commands = setOf(Cmd.UpdatePinnedNotesInCache(model.selectedNotes))
    )

    private fun onBarReplaceToFolderClicked(model: Model): UpdateResult = UpdatedModel(
        model = model,
        effects = setOf(
            Eff.UpdatePassedNotesSharedState(model.selectedNotes.toList()),
            Eff.NavigateToFoldersWithMovingState(model.currentSelectedFolderId)
        )
    )

    private fun onSelectAllNotesClicked(model: Model): UpdateResult {
        val filteredNotes = model.notes.data.filter { note ->
            when (model.currentSelectedFolderId) {
                STICKY_START_FOLDER_ID -> true
                STICKY_END_FOLDER_ID -> note.folderId == DEFAULT_NOTE_FOLDER_ID
                else -> note.folderId == model.currentSelectedFolderId
            }
        }

        val selectedList = model.selectedNotes.toMutableSet().also { list ->
            if (model.currentSelectedFolderId == STICKY_START_FOLDER_ID) {
                if (list.containsAll(model.notes.data)) list.clear()
                else list.addAll(model.notes.data)
            } else {
                if (list.containsAll(filteredNotes)) list.removeAll(filteredNotes.toSet())
                else list.addAll(filteredNotes)
            }
        }.toSet()

        val isVisibleUnpinButton = selectedList.isNotEmpty().run {
            if (this) !selectedList.map { it.isPinned }.contains(false) else false
        }

        return UpdatedModel(
            model.copy(
                selectedNotes = selectedList,
                isVisibleUnpinMainBarIcon = isVisibleUnpinButton
            ),
            effects = setOf(Eff.UpdateSharedUiIsEnabledBottomBarState(selectedList.isNotEmpty()))
        )
    }

    private fun onShareNotesClicked(model: Model): UpdateResult = UpdatedModel(model)

    private fun onCancelSelectionClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(selectedNotes = emptySet(), isSelectionState = false),
        effects = setOf(Eff.UpdateSharedUiIsSelectedState(false))
    )

    private fun onChangeGridCountClicked(model: Model): UpdateResult {
        val gridCount = if (model.gridCount == 1) 2 else 1
        return UpdatedModel(
            model = model.copy(gridCount = gridCount),
            commands = setOf(Cmd.UpdateGridCountInDatastore(gridCount))
        )
    }

    private fun hideRemoveNotesSnackBar(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(
            base = model.base.copy(isLoading = false),
            isVisibleRemovedSnackBar = false,
            removedNotes = emptySet()
        )
    )

    private fun onSnackBarUndoRemoveClicked(model: Model): UpdateResult {
        val restored = model.removedNotes.map { note -> note.copy(isMovedToTrash = false) }
        val notes = model.notes.copy(model.notes.data.toMutableList().also { it.addAll(restored) })
        return UpdatedModel(
            model = model.copy(notes = notes),
            commands = setOf(Cmd.UndoRemoveNotes(restored))
        )
    }

    private fun applyLangToNotesDatestamp(
        model: Model,
        msg: Msg.Inner.FetchedCurrentAppLang
    ): UpdateResult = UpdatedModel(model.copy(currentAppLanguage = msg.language))

    private fun fetchedCurrentPassedFolderId(
        model: Model,
        msg: Msg.Inner.FetchedCurrentFolderIdByPassedState
    ): UpdateResult = UpdatedModel(model.copy(currentSelectedFolderId = msg.id))

    private fun updatedFetchedSortNotesValue(
        model: Model,
        msg: Msg.Inner.UpdatedNotesSortState
    ): UpdateResult {
        val command = if (msg.current == model.currentSortItemSelected) emptySet()
        else setOf(Cmd.UpdateNotesSortValueToDatastore(msg.current))

        return UpdatedModel(model.copy(currentSortItemSelected = msg.current), commands = command)
    }

    private fun updatedFetchedSortPinnedNotesValue(
        model: Model,
        msg: Msg.Inner.UpdatedCheckboxState
    ): UpdateResult {
        val command = if (msg.isSortPinned == model.isSortPinnedNotes) emptySet()
        else setOf(Cmd.UpdateSortCheckboxValueInDatastore(msg.isSortPinned))

        return UpdatedModel(model.copy(isSortPinnedNotes = msg.isSortPinned), commands = command)
    }

    private fun updatedFetchedNotesGridView(
        model: Model,
        msg: Msg.Inner.UpdatedGridViewState
    ): UpdateResult {
        val command = if (msg.count == model.gridCount) emptySet()
        else setOf(Cmd.UpdateGridCountInDatastore(msg.count))

        return UpdatedModel(model.copy(gridCount = msg.count), commands = command)
    }
}