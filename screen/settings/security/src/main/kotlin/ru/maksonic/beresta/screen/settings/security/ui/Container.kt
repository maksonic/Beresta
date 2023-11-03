package ru.maksonic.beresta.screen.settings.security.ui

import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.language_engine.shell.components.LangComponentHiddenNotes
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.routes.settings.SettingsSecurityScreenRouter
import ru.maksonic.beresta.platform.core.ui.findActivity
import ru.maksonic.beresta.platform.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.screen.settings.security.core.Eff
import ru.maksonic.beresta.screen.settings.security.core.Msg
import ru.maksonic.beresta.screen.settings.security.core.SettingsSecuritySandbox

/**
 * @Author maksonic on 03.08.2023
 */
internal typealias Send = (Msg) -> Unit

@Composable
internal fun Container(
    router: SettingsSecurityScreenRouter,
    sandbox: SettingsSecuritySandbox = koinViewModel(),
) {
    val model by sandbox.model.collectAsStateWithLifecycle()
    val activity = LocalContext.current.findActivity()
    val prompt = BiometricPrompt(activity, biometricCallBack(sandbox::send))
    val promptInfo = rememberHiddenNotesBiometricPromptInfo(text.hiddenNotes)

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        showBiometricDialog = { prompt.authenticate(promptInfo) }
    )

    Content(model, sandbox::send)
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: SettingsSecurityScreenRouter,
    showBiometricDialog: () -> Unit
) {
    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            Eff.ShowBiometricDialog -> showBiometricDialog()
        }
    }
}

@Composable
private fun rememberHiddenNotesBiometricPromptInfo(text: LangComponentHiddenNotes) = remember {
    BiometricPrompt.PromptInfo.Builder()
        .setTitle(text.titleSignUpBiometricDialog)
        .setDescription(text.descriptionBiometricDialog)
        .setConfirmationRequired(false)
        .setNegativeButtonText(text.btnTitleNegativeBiometricDialog)
        .build()
}

@Composable
private fun biometricCallBack(send: Send) = remember {
    object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            send(Msg.Inner.UpdatedBiometricState(false))
        }

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            send(Msg.Inner.UpdatedBiometricState(true))
        }
    }
}