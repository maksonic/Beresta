package ru.maksonic.beresta.ui.widget.bar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.button.IconAction

/**
 * @Author maksonic on 16.01.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarNormal(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    title: String,
    tonal: Dp? = Theme.tonal.Level2,
    onBackAction: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {

    val defaultTonal =
        animateDpAsState(
            if (scrollBehavior?.state?.collapsedFraction == 0f) Theme.tonal.Level0
            else Theme.tonal.Level2
        )

    val tonalElevation = tonal ?: defaultTonal.value

    SurfacePro(tonalElevation = tonalElevation) { color ->

        TopAppBar(
            scrollBehavior = scrollBehavior,
            title = { Text(title) },
            navigationIcon = { IconAction(icon = { AppIcon.ArrowBack }, action = onBackAction) },
            actions = actions,
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = color,
                scrolledContainerColor = color,
                titleContentColor = onBackground
            )
        )
    }
}