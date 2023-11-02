package ru.maksonic.beresta.common.ui_kit.bar.top

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.ArrowBack

/**
 * @Author maksonic on 03.04.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCollapsingLarge(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    title: String,
    onBackAction: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {

    val tonal =
        animateDpAsState(
            if (scrollBehavior?.state?.collapsedFraction == 0f) Theme.tonal.level0
            else Theme.tonal.level2, label = "", animationSpec = tween(Theme.animVelocity.common)
        )

    SurfacePro(tonalElevation = tonal.value) { color ->

        LargeTopAppBar(
            scrollBehavior = scrollBehavior,
            title = { Text(title) },
            navigationIcon = { ButtonIcon(icon = AppIcon.ArrowBack, onClick = onBackAction) },
            actions = actions,
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = color,
                scrolledContainerColor = color,
                titleContentColor = onBackground
            )
        )
    }
}