package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.search_bar.core.presentation.Msg
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.widget.functional.clickAction
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 22.02.2023
 */
@Composable
internal fun SearchBarOverflowContainer(
    isExpanded: () -> Boolean,
    send: SendMessage,
    boxScope: BoxWithConstraintsScope,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    val clip = animateDpAsState(if (isExpanded()) 0.dp else 50.dp)
    val topPadding = animateDpAsState(if (isExpanded()) 0.dp else dp4)
    val startPadding = animateDpAsState(if (isExpanded()) 0.dp else dp16)
    val endPadding = animateDpAsState(if (isExpanded()) 0.dp else 68.dp)
    val backgroundColor = animateColorAsState(if (isExpanded()) background else tertiaryContainer)
    val height = animateDpAsState(
        if (isExpanded()) boxScope.maxHeight else Theme.widgetSize.searchBarCollapsedHeight
    )

    Box(
        modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        val clickModifier = if (isExpanded())
            Modifier.noRippleClickable { }
        else Modifier.clickAction(rippleColor = primary) { send(Msg.Ui.OnExpandSearchBarClicked) }

        Box(
            modifier
                .padding(top = topPadding.value)
                .fillMaxWidth()
                .height(height.value)
                .padding(start = startPadding.value, end = endPadding.value)
                .clip(RoundedCornerShape(clip.value))
                .drawBehind { drawRect(backgroundColor.value) }
                .then(clickModifier)
        ) {
            content()
        }
    }
}
