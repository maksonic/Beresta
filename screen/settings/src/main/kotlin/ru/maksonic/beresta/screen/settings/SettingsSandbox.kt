package ru.maksonic.beresta.screen.settings

import androidx.compose.material3.ExperimentalMaterial3Api
import ru.maksonic.beresta.elm.core.ElmUpdate
import ru.maksonic.beresta.elm.core.Sandbox
import ru.maksonic.beresta.screen.settings.ui.widget.ModalSheetContent

/**
 * @Author maksonic on 23.01.2023
 */
private typealias UpdateResult = ElmUpdate<Model, Set<Cmd>, Set<Eff>>

@OptIn(ExperimentalMaterial3Api::class)
class SettingsSandbox(
    program: SettingsProgram
) : Sandbox<Model, Msg, Cmd, Eff>(
    initialModel = Model.Initial,
    initialCmd = setOf(Cmd.FetchCurrentTheme),
    subscriptions = listOf(program)
) {

    override fun update(msg: Msg, model: Model): UpdateResult = when (msg) {
        is Msg.Ui.OnTopBarBackPressed -> onTopBarBackPressed(model)
        is Msg.Ui.OnHideModalSheetClicked -> onHideLanguageSheetClicked(model)
        is Msg.Ui.OnPickLanguageClicked -> onShowLanguageSheetClicked(model)
        is Msg.Ui.OnPickThemeClicked -> onShowThemeSheetClicked(model)
        is Msg.Ui.OnAppearanceClicked -> onAppearanceClicked(model)
        is Msg.Inner.FetchedTheme -> fetchedTheme(model, msg)
        is Msg.Ui.OnAboutAppClicked -> onAboutAppClicked(model)
        is Msg.Ui.OnPrivacyPolicyClicked -> onPrivacyPolicyClicked(model)
        is Msg.Ui.OnUserAccountClicked -> onUserAccountClicked(model)
        is Msg.Ui.OnUserAgreementClicked -> onUserAgreementClicked(model)
        is Msg.Ui.OnWriteEmailClicked -> onWriteEmailClicked(model)
        is Msg.Inner.HiddenModalBottomSheet -> hiddenModalBottomSheet(model)
    }

    private fun fetchedTheme(model: Model, msg: Msg.Inner.FetchedTheme): UpdateResult =
        ElmUpdate(model.copy(currentTheme = msg.theme, isDarkTheme = msg.isDark))

    private fun onTopBarBackPressed(model: Model): UpdateResult =
        ElmUpdate(model.copy(isVisibleModalSheet = false), effects = setOf(Eff.NavigateBack))

    private fun onHideLanguageSheetClicked(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.HideModalSheet))

    private fun onShowLanguageSheetClicked(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(
            isVisibleModalSheet = true,
            currentSheetContent = ModalSheetContent.LANGUAGE_SELECTOR
        ),
    )

    private fun onShowThemeSheetClicked(model: Model): UpdateResult = ElmUpdate(
        model = model.copy(
            isVisibleModalSheet = true,
            currentSheetContent = ModalSheetContent.THEME_SELECTOR
        ),
    )

    private fun onAppearanceClicked(model: Model): UpdateResult =
        ElmUpdate(model, effects = setOf(Eff.NavigateToAppearance))

    private fun onAboutAppClicked(model: Model): UpdateResult = ElmUpdate(model)

    private fun onPrivacyPolicyClicked(model: Model): UpdateResult = ElmUpdate(model)

    private fun onUserAccountClicked(model: Model): UpdateResult = ElmUpdate(model)

    private fun onUserAgreementClicked(model: Model): UpdateResult = ElmUpdate(model)

    private fun onWriteEmailClicked(model: Model): UpdateResult = ElmUpdate(model)

    private fun hiddenModalBottomSheet(model: Model): UpdateResult = ElmUpdate(
        model.copy(
            isVisibleModalSheet = false,
            currentSheetContent = ModalSheetContent.NOTHING
        )
    )
}