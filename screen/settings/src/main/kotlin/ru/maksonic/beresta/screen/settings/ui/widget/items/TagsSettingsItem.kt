package ru.maksonic.beresta.screen.settings.ui.widget.items

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.label.LabelOutlined
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingComponentItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingItem
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.core.Msg
import ru.maksonic.beresta.screen.settings.ui.Send
import ru.maksonic.beresta.screen.settings.ui.widget.BaseSettingComponent

/**
 * @Author maksonic on 11.11.2023
 */
@Composable
internal fun TagsSettingsItem(send: Send) {
    val settings = listOf(
        SettingItem(
            title = text.tags.topBarTitleTagsManagement, prefixIcon = AppIcon.LabelOutlined,
            onClick = { send(Msg.Ui.OnTagsManagementClicked) }
        )
    )
    val component = SettingComponentItem(title = text.tags.topBarTitleTags, items = settings)

    BaseSettingComponent(component)
}