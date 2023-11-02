package ru.maksonic.beresta.common.ui_kit.divider

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_theme.colors.onSecondaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp8

/**
 * @Author maksonic on 17.10.2023
 */
@Composable
fun DividerSecondary(modifier: Modifier = Modifier) {
    HorizontalDivider(
        color = onSecondaryContainer,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = dp16, end = dp16, top = dp8, bottom = dp8)
    )
}