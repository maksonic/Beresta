package ru.maksonic.beresta.common.ui_kit.widget.settings_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.primaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16

/**
 * @Author maksonic on 21.10.2023
 */
@Composable
fun SettingCategoryContainer(
    modifier: Modifier = Modifier,
    containerColor: Color = primaryContainer,
    containerColorState: State<Color> = rememberUpdatedState(containerColor),
    content: @Composable ColumnScope.() -> Unit
) {

    SurfacePro(
        shape = Theme.shape.cornerLarge,
        color = containerColorState.value,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = dp16, start = dp16, end = dp16)
    ) {
        Column {
            content()
        }
    }
}