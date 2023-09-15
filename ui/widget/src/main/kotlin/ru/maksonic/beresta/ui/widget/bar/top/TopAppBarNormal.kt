package ru.maksonic.beresta.ui.widget.bar.top

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.widget.button.ClickableIcon
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

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
        if (scrollBehavior?.state?.overlappedFraction == 0f) Theme.tonal.Level0
        else Theme.tonal.Level2, label = ""
    )
    val tonalElevation = tonal ?: defaultTonal.value

    SurfacePro(tonalElevation = tonalElevation) { color ->
        TopAppBar(
            scrollBehavior = scrollBehavior,
            title = { Text(title) },
            navigationIcon = { ClickableIcon(icon = navIcon, onClick = navIconAction) },
            actions = actions,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = color,
                scrolledContainerColor = color,
                titleContentColor = onBackground
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarDefault(
    title: String = "",
    backgroundColor: State<Color> = remember { mutableStateOf(Color.Transparent) },
    navIcon: ImageVector = AppIcon.ArrowBack,
    navIconAction: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = { ClickableIcon(icon = navIcon, onClick = navIconAction) },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor.value,
            scrolledContainerColor = backgroundColor.value,
            titleContentColor = onBackground
        )
    )
}