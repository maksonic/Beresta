package ru.maksonic.beresta.feature.search_bar.core.ui.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.list.api.domain.DateFormatter
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarState
import ru.maksonic.beresta.feature.search_bar.api.TopSearchBarSharedUiState
import ru.maksonic.beresta.feature.search_bar.api.isCollapsed
import ru.maksonic.beresta.feature.search_bar.api.isExpanded
import ru.maksonic.beresta.feature.search_bar.api.isSelection
import ru.maksonic.beresta.feature.search_bar.api.setBarState
import ru.maksonic.beresta.feature.search_bar.core.Eff
import ru.maksonic.beresta.feature.search_bar.core.Model
import ru.maksonic.beresta.feature.search_bar.core.Msg
import ru.maksonic.beresta.feature.search_bar.core.SearchBarSandbox
import ru.maksonic.beresta.feature.search_bar.core.ui.UserAvatarLayer
import ru.maksonic.beresta.feature.search_bar.core.ui.content.SearchBarCollapsedContent
import ru.maksonic.beresta.feature.search_bar.core.ui.content.SearchBarExpandedContent
import ru.maksonic.beresta.feature.search_bar.core.ui.content.SearchBarSelectedContent
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.onSurfaceVariant
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.SystemStatusBarHeight
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateContent
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp

/**
 * @Author maksonic on 26.04.2023
 */
internal typealias SendMessage = (Msg) -> Unit

class TopSearchBarWidget : SearchBarApi.Ui {
    override val searchBarSharedUiState = TopSearchBarSharedUiState.Initial

    @Composable
    override fun Widget() {
        SearchBarContainer()
    }

    @Composable
    private fun SearchBarContainer(
        sandbox: SearchBarSandbox = koinViewModel(),
        notesListFeatureApi: NotesListApi.Ui = koinInject(),
        topBarCounterFeatureApi: TopBarCounterApi.Ui = koinInject(),
        dateFormatter: DateFormatter = koinInject(),
        modifier: Modifier = Modifier
    ) {
        val model = sandbox.model.collectAsStateWithLifecycle()
        val notesListSharedUiState = notesListFeatureApi.sharedUiState.state.collectAsState()

        HandleUiEffects(effects = sandbox.effects, sharedUiState = searchBarSharedUiState)

        LaunchedEffect(notesListSharedUiState.value.isSelectionState) {
            sandbox.send(
                Msg.Inner.UpdatedSelectedBarState(notesListSharedUiState.value.isSelectionState)
            )
        }

        Box(modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            UserAvatarLayer(notesListSharedUiState)

            SearchBarContent(
                model = model.value,
                send = sandbox::send,
                isColoredBar = notesListSharedUiState.value.isNotColoredTopBar,
                notesListFeatureApi = notesListFeatureApi,
                counterApi = topBarCounterFeatureApi,
                formatter = dateFormatter
            )
        }
    }
}

@Composable
private fun SearchBarContent(
    model: Model,
    send: SendMessage,
    isColoredBar: Boolean,
    notesListFeatureApi: NotesListApi.Ui,
    counterApi: TopBarCounterApi.Ui,
    formatter: DateFormatter,
    modifier: Modifier = Modifier
) {
    val animSpeed = Theme.animSpeed.searchBarTransform
    val initTopPadding = SystemStatusBarHeight.plus(dp4)
    val paddingTop = animateDp(if (model.barState.isCollapsed) initTopPadding else 0.dp, animSpeed)
    val paddingStart = animateDp(if (model.barState.isCollapsed) 68.dp else 0.dp, animSpeed)
    val paddingEnd = animateDp(if (model.barState.isCollapsed) dp16 else 0.dp, animSpeed)
    val cornerRadius = animateIntAsState(
        if (model.barState.isCollapsed) 50 else 0, tween(animSpeed), label = ""
    )

    val tonal = animateDp(
        if (isColoredBar) Theme.tonal.Level0 else Theme.tonal.Level2, Theme.animSpeed.common
    )

    BoxWithConstraints(modifier.fillMaxWidth()) {
        val selectedContainerHeight = Theme.widgetSize.topBarNormalHeight + SystemStatusBarHeight
        val barHeight = animateDp(
            when (model.barState) {
                SearchBarState.Collapsed -> Theme.widgetSize.searchBarCollapsedHeight
                SearchBarState.Expanded -> maxHeight
                SearchBarState.Selected -> selectedContainerHeight
            }, animSpeed
        )

        SurfacePro(
            tonalElevation = tonal.value,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    top = paddingTop.value,
                    start = paddingStart.value,
                    end = paddingEnd.value
                )
                .height(barHeight.value)
                .clip(RoundedCornerShape(cornerRadius.value))
        ) { resultColor ->

            val tween = when (model.barState) {
                SearchBarState.Selected -> Theme.animSpeed.disabled
                else -> Theme.animSpeed.common
            }

            val color = animateColorAsState(
                when (model.barState) {
                    SearchBarState.Expanded -> background
                    SearchBarState.Selected -> resultColor
                    SearchBarState.Collapsed -> {
                        if (isColoredBar) surfaceVariant else onSurfaceVariant
                    }
                }, label = "", animationSpec = tween(tween)
            )
            Box(
                modifier
                    .fillMaxWidth()
                    .height(Theme.widgetSize.searchBarCollapsedHeight)
                    .drawBehind { drawRect(color.value) })
        }

        AnimateContent(model.barState) { state ->
            when {
                state.isExpanded -> {
                    SearchBarExpandedContent(
                        model = model,
                        send = send,
                        notesListFeatureApi = notesListFeatureApi,
                    )
                }

                state.isSelection -> SearchBarSelectedContent(counterApi)
                state.isCollapsed -> {
                    SearchBarCollapsedContent(
                        onExpandClicked = { send(Msg.Ui.OnExpandSearchBarClicked) }
                    )
                }
            }
        }
    }
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    sharedUiState: SharedUiState<TopSearchBarSharedUiState>,
) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.UpdateSharedBarState -> sharedUiState.setBarState(eff.state)
        }
    }
}
