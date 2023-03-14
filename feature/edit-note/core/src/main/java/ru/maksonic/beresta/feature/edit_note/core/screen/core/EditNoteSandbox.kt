package ru.maksonic.beresta.feature.edit_note.core.screen.core

import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.panel.EditorPanelState
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.sheet.BottomSheetEditorState
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.sheet.SheetContent

/**
 * @Author maksonic on 04.03.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class EditNoteSandbox : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model(),
) {
    companion object {
        private const val MAX_NOTE_LENGTH = 15000
    }

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {

        is Msg.Ui.OnTopBarBackPressed -> topBarBackPressed(model)
        is Msg.Ui.OnChangeNoteWallpaperClicked -> onChangeNoteWallpaperPanelClicked(model)
        is Msg.Ui.OnSetIdleEditorPanelStateClicked -> onSetIdleEditorStateClicked(model)
        is Msg.Ui.OnHideModalSheetClicked -> collapsedBottomSheet(model)

        is Msg.Inner.ShowKeyboardWithFocusOnTitle -> onEditFabExpanded(model)
        is Msg.Inner.UpdatedInputTitle -> updatedNoteTitle(model, msg)
        is Msg.Inner.UpdatedInputMessage -> updatedNoteMessage(model, msg)
        is Msg.Inner.UpdatedFabIcon -> updatedCreateNoteFabIcon(model, msg)
        is Msg.Inner.UpdatedEditorPanelVisibility -> updatedEditorPanelVisibility(model, msg)
        is Msg.Inner.UpdatedNoteWallpaper -> updateNoteWallpaper(model, msg)
        is Msg.Inner.FetchedFabStateValue -> afterFetchedFabState(model, msg)
        is Msg.Inner.ShowedMaxLengthNoteInputWarning -> showedToastMaxLengthNoteExceed(model)
        is Msg.Inner.ResetBottomSheetContent -> resetBottomSheetContent(model)
    }

    private fun topBarBackPressed(model: Model): UpdateResult {
        val updatedModel = if (model.editorPanelState != EditorPanelState.IDLE)
            model.copy(editorPanelState = EditorPanelState.IDLE)
        else model

        val backPressedEffect = if (model.isNewNote) Eff.CollapseFab else Eff.NavigateBack
        return UpdatedModel(
            updatedModel.copy(
                editorSheet = model.editorSheet.copy(currentContent = SheetContent.NOTHING)
            ),
            effects = setOf(backPressedEffect)
        )
    }

    private fun onChangeNoteWallpaperPanelClicked(model: Model): UpdateResult =
        UpdatedModel(
            model = model.copy(
                editorSheet = model.editorSheet.copy(
                    state = BottomSheetEditorState.EXPANDED,
                    currentContent = SheetContent.WALLPAPER_SELECTOR
                )
            ),
            effects = setOf(Eff.HideSystemKeyboard)
        )

    private fun onSetIdleEditorStateClicked(model: Model): UpdateResult =
        UpdatedModel(model.copy(editorPanelState = EditorPanelState.IDLE))

    private fun collapsedBottomSheet(model: Model): UpdateResult =
        UpdatedModel(
            model.copy(
                editorSheet = model.editorSheet.copy(state = BottomSheetEditorState.COLLAPSED)
            )
        )

    private fun onEditFabExpanded(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.SetInitialExpandedState))

    private fun afterFetchedFabState(model: Model, msg: Msg.Inner.FetchedFabStateValue): UpdateResult =
        UpdatedModel(model.copy(isNewNote = msg.isExpanded))


    private fun updatedNoteTitle(model: Model, msg: Msg.Inner.UpdatedInputTitle): UpdateResult {
        val croppedInput = msg.textField.text.take(MAX_NOTE_LENGTH)
        val isShowWarning = if (msg.textField.text.length > MAX_NOTE_LENGTH)
            setOf(Eff.ShowToastMaxLengthNoteExceed)
        else
            emptySet()

        return UpdatedModel(
            model = model.copy(titleField = msg.textField.copy(croppedInput)),
            effects = isShowWarning
        )
    }

    private fun updatedNoteMessage(model: Model, msg: Msg.Inner.UpdatedInputMessage): UpdateResult {
        val croppedInput = msg.msgField.text.take(MAX_NOTE_LENGTH)
        val isShowWarning = if (msg.msgField.text.length > MAX_NOTE_LENGTH)
            setOf(Eff.ShowToastMaxLengthNoteExceed)
        else
            emptySet()

        return UpdatedModel(
            model = model.copy(messageField = msg.msgField.copy(croppedInput)),
            effects = isShowWarning
        )
    }

    private fun updateNoteWallpaper(model: Model, msg: Msg.Inner.UpdatedNoteWallpaper): UpdateResult =
        UpdatedModel(model.copy(currentNote = model.currentNote.copy(backgroundId = msg.id)))

    private fun updatedEditorPanelVisibility(
        model: Model, msg: Msg.Inner.UpdatedEditorPanelVisibility
    ): UpdateResult =
        UpdatedModel(model.copy(isVisibleEditorPanel = msg.isVisible))

    private fun showedToastMaxLengthNoteExceed(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.ShowToastMaxLengthNoteExceed))


    private fun updatedCreateNoteFabIcon(model: Model, msg: Msg.Inner.UpdatedFabIcon): UpdateResult {
        val isDraftIcon = model.titleField.text.isNotBlank() || model.messageField.text.isNotBlank()
        msg.fabIconState.value = isDraftIcon
        return UpdatedModel(model)
    }

    private fun resetBottomSheetContent(model: Model): UpdateResult =
        UpdatedModel(
            model.copy(
                editorSheet = model.editorSheet.copy(currentContent = SheetContent.NOTHING)
            )
        )
}

