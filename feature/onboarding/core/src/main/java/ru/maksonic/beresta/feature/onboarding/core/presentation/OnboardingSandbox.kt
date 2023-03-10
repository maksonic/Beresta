package ru.maksonic.beresta.feature.onboarding.core.presentation

import androidx.compose.material.ExperimentalMaterialApi
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.ModalSheetContent

/**
 * @Author maksonic on 15.12.2022
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

@OptIn(ExperimentalMaterialApi::class)
class OnboardingSandbox(
    program: Program
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model(),
    initialEff = setOf(),
    subscriptions = listOf(program)
) {

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Inner.FetchOnboardingTextData -> afterFetchingOnboardingTextFromProvider(model, msg)
        is Msg.Ui.OnGoogleAuthClicked -> UpdatedModel(model)
        is Msg.Ui.OnPrimaryBtnClicked -> onPrimaryBtnClicked(model)
        is Msg.Ui.OnSkipSyncBtnClicked -> onSkipBtnClicked(model)
        is Msg.Inner.Onboardings -> fetchedData(model, msg)
        is Msg.Ui.OnShowSelectLanguageSheetClicked -> onShowLanguageSheetClicked(model)
        is Msg.Ui.OnShowSelectThemeSheetClicked -> onShowThemeSheetClicked(model)
        is Msg.Ui.OnHideLanguageBtnClicked -> onHideLanguageSheetClicked(model)
    }

    private fun afterFetchingOnboardingTextFromProvider(
        model: Model,
        msg: Msg.Inner.FetchOnboardingTextData
    ): UpdateResult = UpdatedModel(model, commands = setOf(Cmd.FetchOnboardings(msg.data)))

    private fun fetchedData(model: Model, msg: Msg.Inner.Onboardings): UpdateResult =
        UpdatedModel(model.copy(onboardings = msg.data))

    private fun onPrimaryBtnClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.SlideNextPage))

    private fun onSkipBtnClicked(model: Model): UpdateResult = UpdatedModel(
        model = model,
        commands = setOf(Cmd.NotShowAgain),
        effects = setOf(Eff.NavigateToMain)
    )

    private fun onShowLanguageSheetClicked(model: Model): UpdateResult =
        UpdatedModel(
            model = model.copy(currentSheetContent = ModalSheetContent.LANGUAGE_SELECTOR),
            effects = setOf(Eff.ShowModalSheet)
        )

    private fun onShowThemeSheetClicked(model: Model): UpdateResult =
        UpdatedModel(
            model = model.copy(currentSheetContent = ModalSheetContent.THEME_SELECTOR),
            effects = setOf(Eff.ShowModalSheet)
        )

    private fun onHideLanguageSheetClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.HideModalSheet))
}
