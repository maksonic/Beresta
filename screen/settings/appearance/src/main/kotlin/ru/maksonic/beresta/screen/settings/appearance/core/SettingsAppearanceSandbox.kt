package ru.maksonic.beresta.screen.settings.appearance.core

import ru.maksonic.beresta.common.ui_theme.AppAnimationVelocity
import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox

/**
 * @Author maksonic on 07.07.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class SettingsAppearanceSandbox(
    program: SettingsAppearanceProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchCardDate),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Inner.FetchedCardDate -> fetchedCardDate(model, msg)
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnCardShapeClicked -> onChangeCardCornersClicked(model, msg)
        is Msg.Ui.OnCardElevationClicked -> onChangeCardElevationClicked(model, msg)
        is Msg.Ui.OnCardWallpaperClicked -> onCardWallpaperVisibilityClicked(model, msg)
        is Msg.Ui.OnCardColorMarkerVisibilityClicked -> onCardMarkerVisibilityClicked(model, msg)
        is Msg.Ui.OnNoteLinesCountClicked -> onChangeCardLinesCountClicked(model)
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

    private fun onTopBarBackPressed(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateBack))

    private fun onChangeCardCornersClicked(
        model: Model,
        msg: Msg.Ui.OnCardShapeClicked
    ): Update = ElmUpdate(model, commands = setOf(Cmd.UpdateNoteCardShape(msg.shape)))

    private fun onChangeCardElevationClicked(
        model: Model,
        msg: Msg.Ui.OnCardElevationClicked
    ): Update = ElmUpdate(model, commands = setOf(Cmd.UpdateNoteCardElevation(msg.isEnabled)))

    private fun onCardWallpaperVisibilityClicked(
        model: Model,
        msg: Msg.Ui.OnCardWallpaperClicked
    ): Update = ElmUpdate(model, commands = setOf(Cmd.UpdateNoteCardWallpaper(msg.isEnabled)))

    private fun onCardMarkerVisibilityClicked(
        model: Model,
        msg: Msg.Ui.OnCardColorMarkerVisibilityClicked
    ): Update =
        ElmUpdate(model, commands = setOf(Cmd.UpdatedNoteCardColorMarkerVisibility(msg.isVisible)))

    private fun onChangeCardLinesCountClicked(model: Model): Update = ElmUpdate(
        model = model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = true, content = ModalSheetContent.NOTE_CARD_LINES_PICKER
            )
        )
    )

    private fun hiddenModalBottomSheet(model: Model): Update = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = false, content = ModalSheetContent.NOTHING
            )
        )
    )

    private fun onModalSheetAcceptClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.HideModalSheet))

    private fun updatedNoteTitleMaxLines(
        model: Model,
        msg: Msg.Inner.UpdatedNoteTitleMaxLines
    ): Update =
        ElmUpdate(model, commands = setOf(Cmd.UpdatedNoteCardTitleMaxLines(msg.value)))

    private fun updatedNoteMessageMaxLines(
        model: Model,
        msg: Msg.Inner.UpdatedNoteMessageMaxLines
    ): Update =
        ElmUpdate(model, commands = setOf(Cmd.UpdatedNoteCardMessageMaxLines(msg.value)))

    private fun onModalSheetLinesPickerDefaultClicked(model: Model): Update =
        ElmUpdate(model, commands = setOf(Cmd.ResetNoteCardLinesByDefault))

    private fun fetchedCardDate(model: Model, msg: Msg.Inner.FetchedCardDate): Update =
        ElmUpdate(model.copy(cardDate = msg.date))

    private fun onChangeAnimationsVelocityClicked(model: Model): Update = ElmUpdate(
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
    ): Update =
        ElmUpdate(model, commands = setOf(Cmd.UpdateAnimationsVelocity(msg.key)))

    private fun onModalSheetAnimationsVelocityDefaultClicked(model: Model): Update =
        ElmUpdate(
            model = model,
            commands = setOf(Cmd.UpdateAnimationsVelocity(AppAnimationVelocity.Key.NORMAL))
        )
}
