package ru.maksonic.beresta.feature.edit_note.core.presentation.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.edit_note.core.Model
import ru.maksonic.beresta.feature.edit_note.core.Msg
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.shadow
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Edit
import ru.maksonic.beresta.ui.widget.functional.clickAction
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 24.02.2023
 */
private const val EXPAND_FAB_ANIMATION_DURATION = 400

@Composable
fun EditNoteExpandableFab(
    model: Model,
    send: SendMessage,
    isScrolledTopNotes: () -> Boolean,
    boxScope: BoxWithConstraintsScope,
    modifier: Modifier = Modifier
) {
    val expandedFabHeight = boxScope.maxHeight
    val expandedFabWidth = boxScope.maxWidth
    val fabSize = Theme.widgetSize.btnFabSize
    val dp0 = 0.dp
    val navBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val height = model.isExpandedEdit.animateDp(start = fabSize, end = expandedFabHeight)
    val width = model.isExpandedEdit.animateDp(start = fabSize, end = expandedFabWidth)
    val bottomPadding = model.isExpandedEdit.animateDp(start = navBarHeight.plus(dp12), end = dp0)
    val endPadding = model.isExpandedEdit.animateDp(start = dp16, end = dp0)
    val clip = animateDpAsState(targetValue = if (model.isExpandedEdit) dp0 else dp16)
    val backgroundColor = animateColorAsState(
        targetValue = if (model.isExpandedEdit) background else primary,
        animationSpec = tween(EXPAND_FAB_ANIMATION_DURATION)
    )
    val elevation = animateDpAsState(
        if (!isScrolledTopNotes() && !model.isExpandedEdit) Theme.elevation.dp4
        else Theme.elevation.disable
    )
    val clickModifier = if (model.isExpandedEdit)
        Modifier.noRippleClickable { }
    else
        Modifier.clickAction(rippleColor = onPrimary) { send(Msg.Ui.OnCreateNewNoteClicked) }

    Box(
        modifier
            .padding(
                end = endPadding.value,
                bottom = bottomPadding.value
            )
            .shadow(elevation.value, RoundedCornerShape(clip.value), spotColor = shadow)
            .size(width = width.value, height = height.value)
            .clip(RoundedCornerShape(clip.value))
            .drawBehind { drawRect(backgroundColor.value) }
            .animateContentSize()
            .then(clickModifier),
        contentAlignment = Alignment.Center
    ) {
        val collapsedContentAlpha = animateFloatAsState(
            targetValue = if (model.isExpandedEdit) 0f else 1f,
            animationSpec = tween(EXPAND_FAB_ANIMATION_DURATION)
        )
        val expandedContentAlpha = animateFloatAsState(
            targetValue = if (model.isExpandedEdit) 1f else 0f,
            animationSpec = tween(EXPAND_FAB_ANIMATION_DURATION)
        )

        if (model.isExpandedEdit) {
            EditNoteExpandedContent(
                send = send,
                topBarColor = { backgroundColor.value },
                modifier = modifier.alpha(expandedContentAlpha.value)
            )
        } else {
            EditNoteCollapsedContent(modifier.alpha(collapsedContentAlpha.value))
        }
    }
}

@Composable
private fun Boolean.animateDp(start: Dp, end: Dp): State<Dp> {
    return animateDpAsState(
        targetValue = if (this) end else start,
        animationSpec = tween(EXPAND_FAB_ANIMATION_DURATION)
    )
}