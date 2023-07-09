package ru.maksonic.beresta.ui.widget.surface

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.ui.theme.color.primaryContainer
import ru.maksonic.beresta.ui.theme.component.dp16

/**
 * @Author maksonic on 07.07.2023
 */
@Composable
fun SettingContainer(
    modifier: Modifier = Modifier,
    containerColor: Color = primaryContainer,
    containerColorState: State<Color> = rememberUpdatedState(containerColor),
    content: @Composable ColumnScope.() -> Unit
) {

    Column(
        modifier
            .fillMaxWidth()
            .padding(top = dp16)
            .drawBehind { drawRect(containerColorState.value) }
    ) {
        content()
    }
}