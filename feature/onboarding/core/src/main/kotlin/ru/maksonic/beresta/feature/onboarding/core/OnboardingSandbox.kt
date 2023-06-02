package ru.maksonic.beresta.feature.onboarding.core

import androidx.compose.material3.ExperimentalMaterial3Api
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.onboarding.core.widget.ModalSheetContent

/**
 * @Author maksonic on 15.12.2022
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

@OptIn(ExperimentalMaterial3Api::class)
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
        is Msg.Inner.UpdatedModalSheetState -> updatedModalSheetState(model, msg)
    }

    private fun onNextPageClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.SlideNextPage))

    private fun onSkipBtnClicked(model: Model): UpdateResult = UpdatedModel(
        model = model,
        commands = setOf(Cmd.NotShowAgain),
        effects = setOf(Eff.NavigateToMain)
    )

    private fun onShowLangPickerClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(
            isVisibleModalSheet = true,
            currentSheetContent = ModalSheetContent.LANGUAGE_SELECTOR
        )
    )

    private fun onShowThemePickerClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(
            isVisibleModalSheet = true,
            currentSheetContent = ModalSheetContent.THEME_SELECTOR
        )
    )

    private fun onHideModalBottomSheet(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.HideModalSheet))

    private fun updatedModalSheetState(
        model: Model,
        msg: Msg.Inner.UpdatedModalSheetState
    ): UpdateResult =
        UpdatedModel(model.copy(isVisibleModalSheet = msg.isVisible))
}
