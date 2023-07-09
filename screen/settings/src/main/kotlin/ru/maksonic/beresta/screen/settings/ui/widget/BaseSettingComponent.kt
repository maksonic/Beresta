package ru.maksonic.beresta.screen.settings.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.ui.widget.button.settings.SettingClickableItem
import ru.maksonic.beresta.ui.widget.button.settings.SettingComponentItem
import ru.maksonic.beresta.ui.widget.surface.SettingContainer
import ru.maksonic.beresta.ui.widget.text.SettingTitle

/**
 * @Author maksonic on 30.04.2023
 */

@Composable
internal fun BaseSettingComponent(
    settingComponent: SettingComponentItem,
    modifier: Modifier = Modifier
) {
    SettingContainer(modifier) {
        SettingTitle(settingComponent.title)

        settingComponent.items.forEach {
            SettingClickableItem(it)
        }
    }
}