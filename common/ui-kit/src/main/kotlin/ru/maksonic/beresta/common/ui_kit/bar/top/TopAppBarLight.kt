package ru.maksonic.beresta.common.ui_kit.bar.top

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.ArrowBack

/**
 * @Author maksonic on 14.10.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarLight(
    title: String = "",
    backgroundColor: State<Color> = remember { mutableStateOf(Color.Transparent) },
    navIcon: ImageVector = AppIcon.ArrowBack,
    navIconAction: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = { ButtonIcon(icon = navIcon, onClick = navIconAction) },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor.value,
            scrolledContainerColor = backgroundColor.value,
            titleContentColor = onBackground
        )
    )
}