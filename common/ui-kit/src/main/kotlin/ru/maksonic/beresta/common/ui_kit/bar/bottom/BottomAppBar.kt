package ru.maksonic.beresta.common.ui_kit.bar.bottom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.colors.surface
import ru.maksonic.beresta.common.ui_theme.colors.transparent
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.noRippleClick
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemNavigationBarHeight

/**
 * @Author maksonic on 07.10.2023
 */
data class BottomBarItem(
    val icon: ImageVector,
    val onClick: () -> Unit,
    val label: String = "",
    val isEmpty: Boolean = false,
)

@Composable
fun BottomAppBar(
    items: Array<BottomBarItem>,
    modifier: Modifier = Modifier,
    isDisabled: Boolean = false,
    containerColor: Color = transparent
) {

    Box {
        Box(modifier.navigationBarsPadding()) {
            NavigationBar(containerColor = containerColor) {
                items.forEach { item ->
                    if (!item.isEmpty) {
                        NavigationBarItem(
                            selected = false,
                            onClick = item.onClick,
                            icon = { Icon(item.icon, "") },
                            label = { Text(item.label) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedTextColor = onBackground,
                                selectedIconColor = onBackground,
                                unselectedIconColor = onBackground,
                                unselectedTextColor = onBackground
                            )
                        )
                    }
                }
            }
        }

        AnimateFadeInOut(visible = isDisabled) {
            Box(
                modifier
                    .fillMaxWidth()
                    .height(Theme.size.bottomMainBarHeight + SystemNavigationBarHeight)
                    .background(surface.copy(alpha = 0.5f))
                    .noRippleClick { }
            )
        }
    }
}