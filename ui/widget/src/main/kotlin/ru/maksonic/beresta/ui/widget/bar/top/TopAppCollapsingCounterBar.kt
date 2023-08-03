package ru.maksonic.beresta.ui.widget.bar.top

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.theme.icons.Close
import ru.maksonic.beresta.ui.widget.button.ClickableIcon
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

/**
 * @Author maksonic on 04.07.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppCollapsingCounterBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    idleTitle: String,
    isSelectionState: State<Boolean> = mutableStateOf(false),
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
    isSelectionState: State<Boolean>,
    count: State<Int>,
    onBackClicked: () -> Unit,
    actions: @Composable RowScope.() -> Unit,
) {
    val tonal =
        animateDp(
            if (scrollBehavior?.state?.collapsedFraction == 0f) Theme.tonal.Level0
            else Theme.tonal.Level2,
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
private fun Title(isSelectionState: State<Boolean>, idleTitle: String, counterValue: State<Int>) {
    Text(
        text = if (isSelectionState.value)
            text.shared.hintSelectedItemsCount.plus(" ${counterValue.value}")
        else idleTitle
    )
}

@Composable
private fun NavigationIcon(isSelectionState: State<Boolean>, onBackClicked: () -> Unit) {
    Crossfade(isSelectionState.value, label = "") {
        ClickableIcon(
            icon = if (it) AppIcon.Close else AppIcon.ArrowBack,
            action = onBackClicked
        )
    }
}