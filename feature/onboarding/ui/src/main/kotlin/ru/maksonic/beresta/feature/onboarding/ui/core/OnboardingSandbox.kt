package ru.maksonic.beresta.feature.onboarding.ui.core

import androidx.compose.material3.ExperimentalMaterial3Api
import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi

/**
 * @Author maksonic on 19.06.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

@OptIn(ExperimentalMaterial3Api::class)
class OnboardingSandbox(program: OnboardingProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnGoogleAuthClicked -> ElmUpdate(model)
        is Msg.Ui.OnNextPageBtnClicked -> onNextPageClicked(model)
        is Msg.Ui.OnSkipSyncBtnClicked -> onSkipBtnClicked(model)
        is Msg.Ui.OnShowLangPickerClicked -> onShowLangPickerClicked(model)
        is Msg.Ui.OnShowThemePickerClicked -> onShowThemePickerClicked(model)
        is Msg.Ui.OnHideModalBottomSheet -> onHideModalBottomSheet(model)
        is Msg.Inner.UpdatedModalSheetVisibility -> updatedSheetVisibility(model, msg)
    }

    private fun onNextPageClicked(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.SlideNextPage))

    private fun onSkipBtnClicked(model: Model): UpdateResult = ElmUpdate(
        model, setOf(Cmd.NotShowAgain), setOf(Eff.NavigateToMain)
    )

    private fun onShowLangPickerClicked(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            isVisibleModalSheet = true,
            currentSheetContent = OnboardingApi.Ui.BottomSheetContent.LANGUAGE_PICKER
        )
    )

    private fun onShowThemePickerClicked(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            isVisibleModalSheet = true,
            currentSheetContent = OnboardingApi.Ui.BottomSheetContent.THEME_PICKER
        )
    )

    private fun onHideModalBottomSheet(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.HideModalSheet))

    private fun updatedSheetVisibility(
        model: Model,
        msg: Msg.Inner.UpdatedModalSheetVisibility
    ): UpdateResult = ElmUpdate(model.copy(isVisibleModalSheet = msg.isVisible))
}