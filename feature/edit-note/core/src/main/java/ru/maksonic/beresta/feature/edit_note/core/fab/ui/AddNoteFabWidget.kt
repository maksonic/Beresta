package ru.maksonic.beresta.feature.edit_note.core.fab.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.edit_note.core.fab.core.AddNoteSandbox
import ru.maksonic.beresta.feature.edit_note.core.fab.core.Eff
import ru.maksonic.beresta.feature.edit_note.core.fab.core.Msg
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.EditNoteScreen
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.dp0
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Edit
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.clickAction

/**
 * @Author maksonic on 23.02.2023
 */
internal typealias FabSendMessage = (Msg) -> Unit

private const val DURATION = 450

class AddNoteFabWidget : EditNoteApi.Ui {

    @Composable
    override fun Widget(isNotesScrollUp: () -> Boolean, modifier: Modifier) {
        Content(isScrollUp = isNotesScrollUp)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Content(
    isScrollUp: () -> Boolean,
    searchBar: SearchBarApi.Ui = get(),
    sandbox: AddNoteSandbox = koinViewModel()
) {
    val model = sandbox.model.collectAsState().value
    val keyboardController = LocalSoftwareKeyboardController.current

    SideEffect {
        if (model.isExpandedFab)
            keyboardController?.show()
        else
            keyboardController?.hide()
    }

    BackHandler(model.isExpandedFab) {
        if (model.isExpandedFab) {
            sandbox.sendMsg(Msg.Ui.OnCollapseFabClicked)
        }
    }

    HandleUiEffects(
        effects = sandbox.effects,
        showSearchBar = {
            searchBar.searchBarVisibilityState.update { true }
        },
        hideSearchBar = { searchBar.searchBarVisibilityState.update { false } }
    )

    FabContainer(
        send = sandbox::sendMsg,
        isExpanded = model.isExpandedFab,
        isScrollUp = isScrollUp,
    )
}

@Composable
private fun FabContainer(
    send: FabSendMessage,
    isExpanded: Boolean,
    isScrollUp: () -> Boolean,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        val fullHeight = this.maxHeight
        val fullWidth = this.maxWidth
        val fabSize = Theme.widgetSize.btnFabSize
        val navBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
        val containerClip = animateDpAsState(if (isExpanded) 0.dp else dp16)
        val containerElevation = animateDpAsState(if (!isScrollUp()) Theme.elevation.dp4 else 0.dp)
        val containerHeight = animateDpAsState(
            if (isExpanded) fullHeight else fabSize, animationSpec = tween(DURATION)
        )
        val containerWidth = animateDpAsState(
            if (isExpanded) fullWidth else fabSize, animationSpec = tween(DURATION)
        )
        val containerBottomPadding = animateDpAsState(
            if (isExpanded) dp0 else navBarHeight.plus(dp12), tween(DURATION)
        )
        val containerEndPadding = animateDpAsState(if (isExpanded) dp0 else dp16, tween(DURATION))
        val expandedContentAlpha =
            animateFloatAsState(if (isExpanded) 1f else 0f, tween(DURATION))

        Box(
            modifier
                .padding(
                    bottom = containerBottomPadding.value,
                    end = containerEndPadding.value
                )
                .height(containerHeight.value)
                .width(containerWidth.value)
                .shadow(
                    containerElevation.value,
                    clip = true,
                    shape = RoundedCornerShape(containerClip.value)
                )
                .clip(RoundedCornerShape(containerClip.value))
                .background(primary),
            contentAlignment = Alignment.BottomEnd
        ) {
            EditNoteScreen(
                isExpandedFab = { isExpanded },
                collapseFabWidget = { send(Msg.Ui.OnCollapseFabClicked) },
                modifier = modifier.alpha(expandedContentAlpha.value),
            )

            AnimateFadeInOut(!isExpanded, tweenValue = DURATION) {
                Box(
                    modifier
                        .fillMaxSize()
                        .clickAction(rippleColor = onPrimary) {
                            send(Msg.Ui.OnCreateNewNoteClicked)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = AppIcon.Edit,
                        tint = onPrimary,
                        contentDescription = "",
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    showSearchBar: () -> Unit,
    hideSearchBar: () -> Unit,
) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.ShowSearchBar -> showSearchBar()
            is Eff.HideSearchBar -> hideSearchBar()
        }
    }
}