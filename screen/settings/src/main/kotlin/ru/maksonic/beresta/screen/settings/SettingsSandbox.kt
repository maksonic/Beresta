package ru.maksonic.beresta.screen.settings

import androidx.compose.material3.ExperimentalMaterial3Api
import ru.maksonic.beresta.elm.Sandbox
import ru.maksonic.beresta.elm.UpdatedModel
import ru.maksonic.beresta.screen.settings.ui.widget.ModalSheetContent

/**
 * @Author maksonic on 23.01.2023
 */
private typealias UpdateResult = UpdatedModel<Model, Set<Cmd>, Set<Eff>>

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
        is Msg.Inner.UpdatedModalSheetState -> updatedModalSheetState(model, msg)

    }

    private fun fetchedTheme(model: Model, msg: Msg.Inner.FetchedTheme): UpdateResult =
        UpdatedModel(model.copy(currentTheme = msg.theme, isDarkTheme = msg.isDark))

    private fun onTopBarBackPressed(model: Model): UpdateResult =
        UpdatedModel(model.copy(isVisibleModalSheet = false), effects = setOf(Eff.NavigateBack))

    private fun onHideLanguageSheetClicked(model: Model): UpdateResult =
        UpdatedModel(model, effects = setOf(Eff.HideModalSheet))

    private fun onShowLanguageSheetClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(
            isVisibleModalSheet = true,
            currentSheetContent = ModalSheetContent.LANGUAGE_SELECTOR
        ),
    )

    private fun onShowThemeSheetClicked(model: Model): UpdateResult = UpdatedModel(
        model = model.copy(
            isVisibleModalSheet = true,
            currentSheetContent = ModalSheetContent.THEME_SELECTOR
        ),
    )

    private fun onAppearanceClicked(model: Model): UpdateResult = UpdatedModel(model)

    private fun onAboutAppClicked(model: Model): UpdateResult = UpdatedModel(model)

    private fun onPrivacyPolicyClicked(model: Model): UpdateResult = UpdatedModel(model)

    private fun onUserAccountClicked(model: Model): UpdateResult = UpdatedModel(model)

    private fun onUserAgreementClicked(model: Model): UpdateResult = UpdatedModel(model)

    private fun onWriteEmailClicked(model: Model): UpdateResult = UpdatedModel(model)

    private fun updatedModalSheetState(
        model: Model,
        msg: Msg.Inner.UpdatedModalSheetState
    ): UpdateResult =
        UpdatedModel(model.copy(isVisibleModalSheet = msg.isVisible))
}