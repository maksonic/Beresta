package ru.maksonic.beresta.common.ui_kit.bar.top

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.ArrowBack
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onBackground

/**
 * @Author maksonic on 19.06.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarNormal(
    title: String = "",
    scrollBehavior: TopAppBarScrollBehavior? = null,
    tonal: Dp? = null,
    navIcon: ImageVector = AppIcon.ArrowBack,
    navIconAction: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val defaultTonal = animateDpAsState(
        if (scrollBehavior?.state?.overlappedFraction == 0f) Theme.tonal.level0
        else Theme.tonal.level2, label = ""
    )
    val tonalElevation = tonal ?: defaultTonal.value

    SurfacePro(tonalElevation = tonalElevation) { color ->
        TopAppBar(
            scrollBehavior = scrollBehavior,
            title = { Text(title) },
            navigationIcon = { ButtonIcon(icon = navIcon, onClick = navIconAction) },
            actions = actions,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = color,
                scrolledContainerColor = color,
                titleContentColor = onBackground
            )
        )
    }
}