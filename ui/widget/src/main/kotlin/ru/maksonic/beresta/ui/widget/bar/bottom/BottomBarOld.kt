package ru.maksonic.beresta.ui.widget.bar.bottom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.transparent

/**
 * @Author maksonic on 22.06.2023
 */
@Composable
fun BottomBarOld(items: Array<BaseBottomBarItem>, modifier: Modifier = Modifier) {
    Box(modifier.navigationBarsPadding()) {
        BottomAppBar(
            modifier.fillMaxWidth(),
            backgroundColor = transparent,
            elevation = Theme.elevation.Level0,
        ) {
            items.forEach { item ->
                if (!item.isEmpty) {
                    BottomRippleBar(
                        selected = true,
                        onClick = item.action,
                        label = { Text(item.label) },
                        icon = { Icon(item.icon, contentDescription = "") },
                        unselectedContentColor = outline,
                        selectedContentColor = onBackground,
                    )
                }
            }
        }
    }
}