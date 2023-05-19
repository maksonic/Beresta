package ru.maksonic.beresta.feature.onboarding.core

import androidx.compose.material.ExperimentalMaterialApi
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.onboarding.core.widget.ModalSheetContent

/**
 * @Author maksonic on 15.12.2022
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

class OnboardingSandbox(
    program: OnboardingProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    subscriptions = listOf(program)
) {

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnGoogleAuthClicked -> UpdatedModel(model)
        is Msg.Ui.OnNextPageBtnClicked -> onNextPageClicked(model)
        is Msg.Ui.OnSkipSyncBtnClicked -> onSkipBtnClicked(model)
        is Msg.Ui.OnShowLangPickerClicked -> onShowLangPickerClicked(model)
        is Msg.Ui.OnShowThemePickerClicked -> onShowThemePickerClicked(model)
        is Msg.Ui.OnHideModalBottomSheet -> onHideModalBottomSheet(model)
    }

    private fun onNextPageClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.SlideNextPage))

    private fun onSkipBtnClicked(model: Model): UpdateResult = UpdatedModel(
        model = model,
        commands = setOf(Cmd.NotShowAgain),
        effects = setOf(Eff.NavigateToMain)
    )

    @OptIn(ExperimentalMaterialApi::class)
    private fun onShowLangPickerClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(currentSheetContent = ModalSheetContent.LANGUAGE_SELECTOR),
        effects = setOf(Eff.ShowModalSheet)
    )

    @OptIn(ExperimentalMaterialApi::class)
    private fun onShowThemePickerClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(currentSheetContent = ModalSheetContent.THEME_SELECTOR),
        effects = setOf(Eff.ShowModalSheet)
    )

    private fun onHideModalBottomSheet(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.HideModalSheet))
}
