package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.notes_list.api.NotesListApi
import ru.maksonic.beresta.feature.search_bar.core.presentation.Model
import ru.maksonic.beresta.feature.search_bar.core.presentation.Msg
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.functional.clickAction
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 24.02.2023
 */
@Composable
internal fun ExpandableSearchBar(
    model: Model,
    send: SendMessage,
    notesList: NotesListApi.Ui,
    boxScope: BoxWithConstraintsScope,
    searchBarCollapsedColor: () -> Color,
    modifier: Modifier = Modifier
) {
    val dp0 = 0.dp
    val expandedBarHeight = boxScope.maxHeight
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val topPadding = animateDpAsState(if (model.isExpandedBar) dp0 else statusBarHeight.plus(20.dp))
    val startPadding = animateDpAsState(if (model.isExpandedBar) dp0 else dp16)
    val endPadding = animateDpAsState(if (model.isExpandedBar) dp0 else 68.dp)
    val backgroundColor =
        animateColorAsState(if (model.isExpandedBar) background else searchBarCollapsedColor())
    val clip = animateDpAsState(if (model.isExpandedBar) dp0 else 50.dp)
    val height = animateDpAsState(
        if (model.isExpandedBar) expandedBarHeight else Theme.widgetSize.searchBarCollapsedHeight,
        animationSpec = tween()
    )
    val clickModifier = if (model.isExpandedBar)
        Modifier.noRippleClickable { }
    else Modifier.clickAction(rippleColor = primary) { send(Msg.Ui.OnExpandSearchBarClicked) }

    Box(
        modifier
            .padding(top = topPadding.value, start = startPadding.value, end = endPadding.value)
            .fillMaxWidth()
            .height(height.value)
            .clip(RoundedCornerShape(clip.value))
            .drawBehind { drawRect(backgroundColor.value) }
            .then(clickModifier)
    ) {
        val collapsedContentAlpha = animateFloatAsState(
            targetValue = if (model.isExpandedBar) 0f else 1f,
            animationSpec = tween()
        )
        val expandedContentAlpha = animateFloatAsState(
            targetValue = if (model.isExpandedBar) 1f else 0f,
            animationSpec = tween()
        )

        if (model.isExpandedBar) {
            SearchBarExpandedContent(
                model = model,
                send = send,
                notesList = notesList,
                modifier = modifier.alpha(expandedContentAlpha.value)
            )
        } else {
            SearchBarCollapsedContent(modifier = modifier.alpha(collapsedContentAlpha.value))
        }
    }
}