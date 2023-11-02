package ru.maksonic.beresta.screen.settings.security.ui.items

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Fingerprint
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.RightPart
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingCategoryContainer
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingClickableItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingTextTitle
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.security.core.Model
import ru.maksonic.beresta.screen.settings.security.core.Msg
import ru.maksonic.beresta.screen.settings.security.ui.Send

/**
 * @Author maksonic on 11.10.2023
 */
@Composable
internal fun HiddenNotesSettingItem(model: Model, send: Send) {
    SettingCategoryContainer {
        SettingTextTitle(title = text.settingsSecurity.titleHiddenNotesBiometric)

        val item = SettingItem(
            title = text.settingsSecurity.itemHiddenNotesBiometric,
            prefixIcon = AppIcon.Fingerprint,
            descriptionHint = text.settingsSecurity.descriptionHiddenNotesVisibility,
            rightPart = RightPart.TOGGLE,
            isEnabledToggle = model.pinPrivacy.isEnabledBiometric,
            onClick = { send(Msg.Ui.OnHiddenNotesBiometricClicked) }
        )

        SettingClickableItem(item)
    }
}
