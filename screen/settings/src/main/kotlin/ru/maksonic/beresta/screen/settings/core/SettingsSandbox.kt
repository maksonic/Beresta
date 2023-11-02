package ru.maksonic.beresta.screen.settings.core

import ru.maksonic.beresta.platform.elm.core.ElmUpdate
import ru.maksonic.beresta.platform.elm.core.Sandbox

/**
 * @Author maksonic on 23.01.2023
 */
private typealias Update = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

class SettingsSandbox(
    program: SettingsProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchCurrentTheme),
    subscriptions = listOf(program)
) {
    override fun update(msg: Msg, model: Model): Update = when (msg) {
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnHideModalSheetClicked -> onHideLanguageSheetClicked(model)
        is Msg.Ui.OnPickLanguageClicked -> onShowLanguageSheetClicked(model)
        is Msg.Ui.OnPickThemeClicked -> onShowThemeSheetClicked(model)
        is Msg.Ui.OnAppearanceClicked -> onAppearanceClicked(model)
        is Msg.Ui.OnNotificationsClicked -> onNotificationsClicked(model)
        is Msg.Ui.OnSecurityClicked -> onSecurityClicked(model)
        is Msg.Inner.FetchedTheme -> fetchedTheme(model, msg)
        is Msg.Ui.OnAboutAppClicked -> onAboutAppClicked(model)
        is Msg.Ui.OnPrivacyPolicyClicked -> onPrivacyPolicyClicked(model)
        is Msg.Ui.OnUserAccountClicked -> onUserAccountClicked(model)
        is Msg.Ui.OnUserAgreementClicked -> onUserAgreementClicked(model)
        is Msg.Ui.OnWriteEmailClicked -> onWriteEmailClicked(model)
        is Msg.Inner.HiddenModalBottomSheet -> hiddenModalBottomSheet(model)
    }

    private fun fetchedTheme(model: Model, msg: Msg.Inner.FetchedTheme): Update =
        ElmUpdate(model.copy(currentTheme = msg.theme, isDarkTheme = msg.isDark))

    private fun onTopBarBackPressed(model: Model): Update =
        ElmUpdate(
            model.copy(modalSheet = model.modalSheet.copy(isVisible = false)),
            effects = setOf(Eff.NavigateBack)
        )

    private fun onHideLanguageSheetClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.HideModalSheet))

    private fun onShowLanguageSheetClicked(model: Model): Update = ElmUpdate(
        model = model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = true, content = ModalSheetContent.LANGUAGE_PICKER
            )
        ),
    )

    private fun onShowThemeSheetClicked(model: Model): Update = ElmUpdate(
        model = model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = true, content = ModalSheetContent.THEME_PICKER
            )
        ),
    )

    private fun onAppearanceClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateToAppearance))

    private fun onNotificationsClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateToNotifications))

    private fun onSecurityClicked(model: Model): Update =
        ElmUpdate(model, effects = setOf(Eff.NavigateToSecurity))

    private fun onAboutAppClicked(model: Model): Update = ElmUpdate(model)

    private fun onPrivacyPolicyClicked(model: Model): Update = ElmUpdate(model)

    private fun onUserAccountClicked(model: Model): Update = ElmUpdate(model)

    private fun onUserAgreementClicked(model: Model): Update = ElmUpdate(model)

    private fun onWriteEmailClicked(model: Model): Update = ElmUpdate(model)

    private fun hiddenModalBottomSheet(model: Model): Update = ElmUpdate(
        model = model.copy(
            modalSheet = model.modalSheet.copy(
                isVisible = false, content = ModalSheetContent.NOTHING
            )
        )
    )
}