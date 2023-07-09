package ru.maksonic.beresta.screen.settings.appearance.core

import androidx.compose.material3.ExperimentalMaterial3Api
import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox

/**
 * @Author maksonic on 07.07.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class SettingsAppearanceSandbox(
    program: SettingsAppearanceProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnNoteCardShapeClicked -> onChangeCardCornersClicked(model, msg)
        is Msg.Ui.OnNoteCardElevationClicked -> onChangeCardElevationClicked(model, msg)
        is Msg.Ui.OnNoteLinesCountClicked -> onChangeCardLinesCountClicked(model)
        is Msg.Inner.HiddenModalBottomSheet -> hiddenModalBottomSheet(model)
        is Msg.Ui.OnModalSheetLinesPickerSaveClicked -> onModalSheetLinesPickerSaveClicked(model)
        is Msg.Inner.UpdatedNoteTitleMaxLines -> updatedNoteTitleMaxLines(model, msg)
        is Msg.Inner.UpdatedNoteMessageMaxLines -> updatedNoteMessageMaxLines(model, msg)
        is Msg.Ui.OnModalSheetLinesPickerDefaultClicked -> {
            onModalSheetLinesPickerDefaultClicked(model)
        }
    }

    private fun onTopBarBackPressed(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.NavigateBack))

    private fun onChangeCardCornersClicked(
        model: Model,
        msg: Msg.Ui.OnNoteCardShapeClicked
    ): UpdateResult = ElmUpdate(model, commands = setOf(Cmd.UpdateNoteCardShape(msg.shape)))

    private fun onChangeCardElevationClicked(
        model: Model,
        msg: Msg.Ui.OnNoteCardElevationClicked
    ): UpdateResult = ElmUpdate(model, commands = setOf(Cmd.UpdateNoteCardElevation(msg.isEnabled)))

    @OptIn(ExperimentalMaterial3Api::class)
    private fun onChangeCardLinesCountClicked(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = true,
                content = ModalSheetContent.NOTE_CARD_LINES_PICKER
            )
        )
    )

    @OptIn(ExperimentalMaterial3Api::class)
    private fun hiddenModalBottomSheet(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = false, content = ModalSheetContent.NOTHING
            )
        )
    )

    private fun onModalSheetLinesPickerSaveClicked(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.HideModalSheet))

    private fun updatedNoteTitleMaxLines(
        model: Model,
        msg: Msg.Inner.UpdatedNoteTitleMaxLines
    ): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.UpdatedNoteCardTitleMaxLines(msg.value)))

    private fun updatedNoteMessageMaxLines(
        model: Model,
        msg: Msg.Inner.UpdatedNoteMessageMaxLines
    ): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.UpdatedNoteCardMessageMaxLines(msg.value)))

    private fun onModalSheetLinesPickerDefaultClicked(model: Model): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.ResetNoteCardLinesByDefault))

}