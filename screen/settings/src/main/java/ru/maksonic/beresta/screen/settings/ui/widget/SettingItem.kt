package ru.maksonic.beresta.screen.settings.ui.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16

/**
 * @Author maksonic on 20.02.2023
 */
@Composable
internal fun SettingTitle(title: String, modifier: Modifier = Modifier) {
    Text(title, style = TextDesign.topBar.copy(color = primary), modifier = modifier.padding(dp16))
}