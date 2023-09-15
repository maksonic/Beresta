package ru.maksonic.beresta.screen.settings.appearance.core

import androidx.compose.material3.ExperimentalMaterial3Api
import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox
import ru.maksonic.beresta.ui.theme.component.AppAnimationVelocity

/**
 * @Author maksonic on 07.07.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class SettingsAppearanceSandbox(
    program: SettingsAppearanceProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchCurrentAppLang),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchedAppLang -> fetchedAppLang(model, msg)
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnNoteCardShapeClicked -> onChangeCardCornersClicked(model, msg)
        is Msg.Ui.OnNoteCardElevationClicked -> onChangeCardElevationClicked(model, msg)
        is Msg.Ui.OnNoteLinesCountClicked -> onChangeCardLinesCountClicked(model)
        is Msg.Ui.OnNoteCardColorMarkerVisibilityClicked -> {
            onNoteCardColorMarkerVisibilityClicked(model, msg)
        }

        is Msg.Inner.HiddenModalBottomSheet -> hiddenModalBottomSheet(model)
        is Msg.Ui.OnModalSheetAcceptClicked -> onModalSheetAcceptClicked(model)
        is Msg.Inner.UpdatedNoteTitleMaxLines -> updatedNoteTitleMaxLines(model, msg)
        is Msg.Inner.UpdatedNoteMessageMaxLines -> updatedNoteMessageMaxLines(model, msg)
        is Msg.Ui.OnModalSheetLinesPickerDefaultClicked -> {
            onModalSheetLinesPickerDefaultClicked(model)
        }

        is Msg.Ui.OnAnimationsVelocityClicked -> onChangeAnimationsVelocityClicked(model)
        is Msg.Inner.UpdatedAnimationsVelocity -> updatedAnimationsVelocity(model, msg)
        is Msg.Ui.OnModalSheetAnimationsVelocityDefaultClicked -> {
            onModalSheetAnimationsVelocityDefaultClicked(model)
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

    private fun onNoteCardColorMarkerVisibilityClicked(
        model: Model,
        msg: Msg.Ui.OnNoteCardColorMarkerVisibilityClicked
    ): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.UpdatedNoteCardColorMarkerVisibility(msg.isVisible)))

    @OptIn(ExperimentalMaterial3Api::class)
    private fun hiddenModalBottomSheet(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = false, content = ModalSheetContent.NOTHING
            )
        )
    )

    private fun onModalSheetAcceptClicked(model: Model): UpdateResult =
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

    private fun fetchedAppLang(model: Model, msg: Msg.Inner.FetchedAppLang): UpdateResult =
        ElmUpdate(model.copy(currentLang = msg.lang))

    @OptIn(ExperimentalMaterial3Api::class)
    private fun onChangeAnimationsVelocityClicked(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = true,
                content = ModalSheetContent.ANIMATIONS_VELOCITY_PICKER
            )
        )
    )

    private fun updatedAnimationsVelocity(
        model: Model,
        msg: Msg.Inner.UpdatedAnimationsVelocity
    ): UpdateResult =
        ElmUpdate(model, commands = setOf(Cmd.UpdateAnimationsVelocity(msg.key)))

    private fun onModalSheetAnimationsVelocityDefaultClicked(model: Model): UpdateResult =
        ElmUpdate(
            model = model,
            commands = setOf(Cmd.UpdateAnimationsVelocity(AppAnimationVelocity.Key.NORMAL))
        )
}
