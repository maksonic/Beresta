package ru.maksonic.beresta.ui.widget.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16

/**
 * @Author maksonic on 07.07.2023
 */
@Composable
fun SettingTitle(title: String, modifier: Modifier = Modifier) {
    Row(
        modifier.height(Theme.widgetSize.minimumTouchTargetSize),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = TextDesign.topBar.copy(color = onPrimary),
            modifier = modifier.padding(start = dp16, end = dp16)
        )
    }
}