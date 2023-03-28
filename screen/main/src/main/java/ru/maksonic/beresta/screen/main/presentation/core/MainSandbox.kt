package ru.maksonic.beresta.screen.main.presentation.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.notes_list.api.ui.MainBottomBarState
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi

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
        is Msg.Ui.OnBottomBarSettingsClicked -> onSettingsClicked(model)
        is Msg.Ui.OnBottomBarTrashClicked -> onTrashClicked(model)
        is Msg.Ui.OnBottomBarOpenFoldersClicked -> UpdatedModel(model)
        is Msg.Ui.OnBottomBarSortNotesByClicked -> UpdatedModel(model)
        is Msg.Ui.OnSwitchViewClicked -> onSwitchGridCountClicked(model)
        is Msg.Ui.OnChipFilterClicked -> onFilterChipClicked(model, msg)
        is Msg.Ui.OnNoteClicked -> onNoteClicked(model, msg)
        is Msg.Ui.OnNoteLongPressed -> onNoteLongClicked(model, msg)
        is Msg.Ui.OnHideSelectedNotesBottomBarClicked -> UpdatedModel(model)
        is Msg.Ui.OnPinSelectedNotesBottomBarClicked -> onPinSelectedNotesClicked(model)
        is Msg.Ui.OnRemoveSelectedNotesBottomBarClicked -> onRemoveSelectedNotesClicked(model)
        is Msg.Ui.OnReplaceFolderSelectedNotesBottomBarClicked -> UpdatedModel(model)
        is Msg.Ui.OnCancelSelectionClicked -> cancelNotesSelection(model)
        is Msg.Ui.OnSelectAllNotesClicked -> onSelectAllNotesClicked(model)
    }

    private fun fetchedNotes(model: Model, msg: Msg.Inner.FetchedNotesCollection): UpdateResult {
        val sortNotes =
            msg.notes.copy(
                data = msg.notes.data.sortedWith(
                    comparator = compareByDescending<NoteUi> { it.isPinned }.thenBy { it.id }
                )
            )
        return UpdatedModel(
            model = model.copy(
                base = model.base.copy(isLoading = false, isSuccessLoading = true, isError = false),
                notes = sortNotes
            )
        )
    }

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

    private fun onSwitchGridCountClicked(model: Model): UpdateResult {
        val count = if (model.notesGridCount == 1) 2 else 1
        return UpdatedModel(model.copy(notesGridCount = count))
    }
    private fun onFilterChipClicked(model: Model, msg: Msg.Ui.OnChipFilterClicked): UpdateResult {
        val afterSelect = model.filters.copy(data = model.filters.data.map { note ->
            note.copy(isSelected = note.id == msg.id)
        })
        return UpdatedModel(model.copy(filters = afterSelect))
    }

    private fun onNoteClicked(model: Model, msg: Msg.Ui.OnNoteClicked): UpdateResult =
        if (model.isSelectionState)
            baseOnNoteAction(model, msg.id)
        else
            UpdatedModel(
                model = model.copy(bottomBarState = MainBottomBarState.IDLE),
                effects = setOf(Eff.ShowNoteForEdit(msg.id))
            )


    private fun onNoteLongClicked(model: Model, msg: Msg.Ui.OnNoteLongPressed): UpdateResult =
        baseOnNoteAction(model, msg.id)

    private fun baseOnNoteAction(model: Model, noteId: Long): UpdateResult {
        var counter = model.selectedNotesCount
        val updateCounter = { isSelected: Boolean -> if (isSelected) counter++ else counter-- }
        val afterSelectNotes = model.notes.copy(data = model.notes.data.map { note ->
            if (note.id == noteId) updateCounter(!note.isSelected)
            return@map if (note.id == noteId) note.copy(isSelected = !note.isSelected) else note
        })
        val isSelected = afterSelectNotes.data.map { it.isSelected }.contains(true)
        val selectedNotes = afterSelectNotes.data.filter { it.isSelected }
        val isShowUnpinButton = !selectedNotes.map { it.isPinned }.contains(false)
        val bottomBarState =
            if (isSelected) MainBottomBarState.SELECTION else MainBottomBarState.IDLE

        return UpdatedModel(
            model.copy(
                notes = afterSelectNotes,
                isSelectionState = isSelected,
                bottomBarState = bottomBarState,
                isShowBottomBarUnpinBtn = isShowUnpinButton,
                selectedNotesCount = counter
            )
        )
    }

    private fun onSelectAllNotesClicked(model: Model): UpdateResult {
        var selectedCount = 0
        val isAllSelected = model.notes.data.all { note -> note.isSelected }
        val notes = model.notes.copy(data = model.notes.data.map { note ->
            selectedCount = if (isAllSelected) 0 else model.notes.data.map { it.isSelected }.count()
            return@map note.copy(isSelected = !isAllSelected)
        })
        val isSelected = notes.data.map { it.isSelected }.contains(true)
        val isShowUnpinButton = notes.data.all { note -> note.isPinned }
        val bottomBarState =
            if (isSelected) MainBottomBarState.SELECTION else MainBottomBarState.IDLE

        return UpdatedModel(
            model = model.copy(
                notes = notes,
                isSelectionState = isSelected,
                isShowBottomBarUnpinBtn = isShowUnpinButton,
                selectedNotesCount = selectedCount,
                bottomBarState = bottomBarState
            ),
        )
    }

    private fun cancelNotesSelection(model: Model): UpdateResult {
        val unselectedAll =
            model.notes.copy(data = model.notes.data.map { it.copy(isSelected = false) })
        return UpdatedModel(
            model = model.copy(
                notes = unselectedAll,
                isSelectionState = false,
                selectedNotesCount = 0,
                bottomBarState = MainBottomBarState.IDLE
            )
        )
    }

    private fun onPinSelectedNotesClicked(model: Model): UpdateResult {
        val selectedNotes = model.notes.copy(data = model.notes.data.filter { it.isSelected })
        val isSelectedContainsUnpinnedNotes = selectedNotes.data.map { it.isPinned }.contains(false)
        val notes = model.notes.copy(data = model.notes.data.map { note ->
            val isPinned = if (isSelectedContainsUnpinnedNotes) true else !note.isPinned
            return@map if (note.isSelected) note.copy(isPinned = isPinned) else note
        })

        return UpdatedModel(
            model = model.copy(notes = notes),
            commands = setOf(Cmd.UpdatePinnedNotesInCache(notes.data))
        )
    }

    private fun replaceSelectedNotesToFolder(
        model: Model, msg: Msg.Ui.OnReplaceFolderSelectedNotesBottomBarClicked
    ): UpdateResult = UpdatedModel(model)

    private fun onRemoveSelectedNotesClicked(model: Model): UpdateResult {
        val remove = model.notes.copy(data = model.notes.data.map { note ->
            return@map note.copy(isMovedToTrash = note.isSelected)
        })

        val notes = remove.copy(data = remove.data.filter { !it.isMovedToTrash })

        return UpdatedModel(
            model.copy(
                notes = notes,
                isSelectionState = false,
                bottomBarState = MainBottomBarState.IDLE,
                selectedNotesCount = 0
            ),
            commands = setOf(Cmd.RemoveSelected(remove.data))
        )
    }
}