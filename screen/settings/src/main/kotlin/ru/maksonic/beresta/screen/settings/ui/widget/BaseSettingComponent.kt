package ru.maksonic.beresta.screen.settings.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingCategoryContainer
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingClickableItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingComponentItem
import ru.maksonic.beresta.common.ui_kit.widget.settings_screen.SettingTextTitle

/**
 * @Author maksonic on 30.04.2023
 */

@Composable
internal fun BaseSettingComponent(
    settingComponent: SettingComponentItem,
    modifier: Modifier = Modifier
) {
    SettingCategoryContainer(modifier) {
        SettingTextTitle(settingComponent.title)

        settingComponent.items.forEach {
            SettingClickableItem(it)
        }
    }
}