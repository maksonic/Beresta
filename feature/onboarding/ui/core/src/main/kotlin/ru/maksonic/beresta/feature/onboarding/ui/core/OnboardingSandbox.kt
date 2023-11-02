package ru.maksonic.beresta.feature.onboarding.ui.core

import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox

/**
 * @Author maksonic on 19.06.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class OnboardingSandbox(program: OnboardingProgram) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchOnboardingsProvider),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnGoogleAuthClicked -> ElmUpdate(model)
        is Msg.Ui.OnNextPageBtnClicked -> onNextPageClicked(model)
        is Msg.Ui.OnSkipSyncBtnClicked -> onSkipBtnClicked(model)
        is Msg.Ui.OnShowLangPickerClicked -> onShowLangPickerClicked(model)
        is Msg.Ui.OnShowThemePickerClicked -> onShowThemePickerClicked(model)
        is Msg.Inner.HiddenModalBottomSheet -> hiddenModalBottomSheet(model)
        is Msg.Inner.FetchedOnboardingsData -> fetchedOnboardingsProvider(model, msg)
        is Msg.Inner.FetchedOnboardingsFailure -> fetchedOnboardingsFailure(model)
    }

    private fun onNextPageClicked(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.SlideNextPage))

    private fun onSkipBtnClicked(model: Model): UpdateResult = ElmUpdate(
        model, setOf(Cmd.NotShowAgain), setOf(Eff.NavigateToMain)
    )

    private fun onShowLangPickerClicked(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = true, content = ModalSheetContent.LANGUAGE_PICKER
            )
        )
    )

    private fun onShowThemePickerClicked(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = true, content = ModalSheetContent.THEME_PICKER
            )
        )
    )

    private fun hiddenModalBottomSheet(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = false, content = ModalSheetContent.NOTHING
            )
        )
    )

    private fun fetchedOnboardingsProvider(
        model: Model,
        msg: Msg.Inner.FetchedOnboardingsData
    ): UpdateResult = ElmUpdate(model.copy(onboardings = msg.onboardings))

    private fun fetchedOnboardingsFailure(model: Model): UpdateResult =
        ElmUpdate(model.copy(onboardings = emptyList(), isFailFetched = true))
}