package ru.maksonic.beresta.feature.edit_note.core.screen.core

import androidx.compose.material.ExperimentalMaterialApi
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.ModalSheetContent
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.editor.EditorPanelState

/**
 * @Author maksonic on 04.03.2023
 */
private typealias Result = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

@OptIn(ExperimentalMaterialApi::class)
class EditNoteSandbox : Sandbox<Model, Msg, Cmd, Eff>(initialModel = Model()) {
    companion object {
        private const val MAX_NOTE_LENGTH = 15000
    }

    override fun update(msg: Msg, model: Model): Result = when (msg) {

        is Msg.Ui.OnTopBarBackPressed -> topBarBackPressed(model)
        is Msg.Inner.UpdatedInputTitle -> updatedNoteTitle(model, msg)
        is Msg.Inner.UpdatedInputMessage -> updatedNoteMessage(model, msg)
        is Msg.Inner.FetchedFabStateValue -> afterFetchedFabState(model, msg)
        is Msg.Inner.UpdatedEditorPanelVisibility -> updatedEditorPanelVisibility(model, msg)
        is Msg.Inner.ShowedMaxLengthNoteInputWarning -> showedToastMaxLengthNoteExceed(model)
        is Msg.Inner.HideKeyboard -> UpdatedModel(model, effects = setOf(Eff.HideSystemKeyboard))
        is Msg.Inner.ShowKeyboard -> UpdatedModel(model, effects = setOf(Eff.ShowSystemKeyboard))
        is Msg.Ui.OnChangeNoteWallpaperClicked -> onChangeNoteWallpaperPanelClicked(model)
        is Msg.Ui.OnSetIdleEditorPanelStateClicked -> onSetIdleEditorStateClicked(model)
        is Msg.Ui.OnHideModalSheetClicked -> onHideWallpaperSelectorSheetClicked(model)
    }

    private fun afterFetchedFabState(model: Model, msg: Msg.Inner.FetchedFabStateValue): Result =
        UpdatedModel(model.copy(isExpandedFabState = msg.isExpanded))

    private fun topBarBackPressed(model: Model): Result {
        val updatedModel = if (model.editorPanelState != EditorPanelState.IDLE) {
            model.copy(editorPanelState = EditorPanelState.IDLE)
        } else {
            model
        }

        val backPressedEffect = if (model.editorPanelState != EditorPanelState.IDLE) {
            emptySet()
        } else {
            if (model.isExpandedFabState)
                setOf(Eff.CollapseFab)
            else
                setOf(Eff.NavigateBack)
        }

        return UpdatedModel(updatedModel, effects = backPressedEffect)
    }

    private fun updatedNoteTitle(model: Model, msg: Msg.Inner.UpdatedInputTitle): Result {
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

    private fun updatedNoteMessage(model: Model, msg: Msg.Inner.UpdatedInputMessage): Result {
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

    private fun updatedEditorPanelVisibility(
        model: Model, msg: Msg.Inner.UpdatedEditorPanelVisibility
    ): Result =
        UpdatedModel(model.copy(isVisibleEditorPanel = msg.isVisible))

    private fun showedToastMaxLengthNoteExceed(model: Model): Result =
        UpdatedModel(model, effects = setOf(Eff.ShowToastMaxLengthNoteExceed))

    private fun onChangeNoteWallpaperPanelClicked(model: Model): Result = UpdatedModel(
        model = model.copy(currentSheetContent = ModalSheetContent.WALLPAPER_SELECTOR),
        effects = setOf(Eff.ShowModalSheet, Eff.HideSystemKeyboard)
    )

    private fun onSetIdleEditorStateClicked(model: Model): Result =
        UpdatedModel(model.copy(editorPanelState = EditorPanelState.IDLE))

    private fun onHideWallpaperSelectorSheetClicked(model: Model): Result =
        UpdatedModel(model, effects = setOf(Eff.HideModalSheet))
}

