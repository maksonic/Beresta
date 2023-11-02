package ru.maksonic.beresta.common.ui_kit.bar.top

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.ArrowBack
import ru.maksonic.beresta.common.ui_kit.icons.Close
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme

/**
 * @Author maksonic on 04.07.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppCollapsingCounterBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    idleTitle: String,
    isSelectionState: Boolean = false,
    count: State<Int> = mutableIntStateOf(1),
    onBackClicked: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    LargeTopBarCounterContent(
        scrollBehavior = scrollBehavior,
        isSelectionState = isSelectionState,
        count = count,
        idleTitle = idleTitle,
        onBackClicked = onBackClicked,
        actions = actions
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LargeTopBarCounterContent(
    scrollBehavior: TopAppBarScrollBehavior?,
    idleTitle: String,
    isSelectionState: Boolean,
    count: State<Int>,
    onBackClicked: () -> Unit,
    actions: @Composable RowScope.() -> Unit,
) {
    val tonal = animateDpAsState(
        if (scrollBehavior?.state?.collapsedFraction == 0f) Theme.tonal.level0
        else Theme.tonal.level2,
        label = "",
    )

    SurfacePro(tonalElevation = tonal.value) { color ->
        LargeTopAppBar(
            scrollBehavior = scrollBehavior,
            title = { Title(isSelectionState, idleTitle, counterValue = count) },
            navigationIcon = { NavigationIcon(isSelectionState, onBackClicked) },
            actions = actions,
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = color,
                scrolledContainerColor = color,
                titleContentColor = onBackground
            )
        )
    }
}

@Composable
private fun Title(isSelectionState: Boolean, idleTitle: String, counterValue: State<Int>) {
    Text(
        text = if (isSelectionState)
            text.shared.hintSelectedItemsCount.plus(" ${counterValue.value}")
        else idleTitle
    )
}

@Composable
private fun NavigationIcon(isSelectionState: Boolean, onBackClicked: () -> Unit) {
    Crossfade(isSelectionState, label = "") {
        ButtonIcon(
            icon = if (it) AppIcon.Close else AppIcon.ArrowBack,
            onClick = onBackClicked
        )
    }
}