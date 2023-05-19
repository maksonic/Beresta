package ru.maksonic.beresta.feature.top_bar_counter.core

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.theme.icons.Close
import ru.maksonic.beresta.ui.theme.icons.SelectAll
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateContent
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 16.05.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LargeTopBarCounterContainer(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    isSelectionState: Boolean,
    count: Int,
    idleTitle: String,
    onBackClicked: () -> Unit,
    onCancelSelectStateClicked: () -> Unit,
    onSelectAllItemsClicked: () -> Unit,
) {
    LargeTopBarCounterContent(
        scrollBehavior = scrollBehavior,
        isSelectionState = isSelectionState,
        count = count,
        idleTitle = idleTitle,
        onBackClicked = onBackClicked,
        onCancelClicked = onCancelSelectStateClicked,
        onSelectAllItemsClicked = onSelectAllItemsClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LargeTopBarCounterContent(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    isSelectionState: Boolean,
    count: Int,
    idleTitle: String,
    onBackClicked: () -> Unit,
    onCancelClicked: () -> Unit,
    onSelectAllItemsClicked: () -> Unit,
) {
    val tonal =
        animateDpAsState(
            if (scrollBehavior?.state?.collapsedFraction == 0f) Theme.tonal.Level0
            else Theme.tonal.Level2, label = "", animationSpec = tween(Theme.animSpeed.common)
        )

    SurfacePro(tonalElevation = tonal.value) { color ->

        LargeTopAppBar(
            scrollBehavior = scrollBehavior,
            title = { Title(isSelectionState, idleTitle, counterValue = count) },
            navigationIcon = { NavigationIcon(isSelectionState, onBackClicked, onCancelClicked) },
            actions = { SelectAllIcon(isSelectionState, onSelectAllItemsClicked) },
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = color,
                scrolledContainerColor = color,
                titleContentColor = onBackground
            )
        )
    }
}

@Composable
private fun Title(isSelectionState: Boolean, idleTitle: String, counterValue: Int) {
    AnimateContent(isSelectionState) {
        if (it) {
            Text(text = text.shared.hintSelectedItemsCount.plus(" $counterValue"))
        } else {
            Text(idleTitle)
        }
    }
}

@Composable
private fun NavigationIcon(
    isSelectionState: Boolean,
    onBackClicked: () -> Unit,
    onCancelSelectStateClicked: () -> Unit,
) {
    AnimateContent(isSelectionState) {
        if (it) {
            IconAction(icon = { AppIcon.Close }, action = onCancelSelectStateClicked)
        } else {
            IconAction(icon = { AppIcon.ArrowBack }, action = onBackClicked)
        }
    }
}

@Composable
fun SelectAllIcon(isSelectionState: Boolean, onSelectAllItemsClicked: () -> Unit) {
    AnimateFadeInOut(isSelectionState) {
        IconAction(icon = { AppIcon.SelectAll }, action = onSelectAllItemsClicked)
    }
}