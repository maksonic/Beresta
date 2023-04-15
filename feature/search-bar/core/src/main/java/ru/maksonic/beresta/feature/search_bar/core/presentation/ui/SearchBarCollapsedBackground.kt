package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.core.R
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Share
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.functional.clickAction

/**
 * @Author maksonic on 22.02.2023
 */
@Composable
internal fun SearchBarCollapsedBackground(
    isSelectionState: State<Boolean>,
    isVisibleFirstNoteOffset: State<Boolean>,
    modifier: Modifier = Modifier
) {
    val shareIconTransition =
        animateDpAsState(if (isSelectionState.value) 0.dp else 68.dp, tween())
    val tonal = animateDpAsState(
        if (isVisibleFirstNoteOffset.value) Theme.tonal.Level0 else Theme.tonal.Level4
    )

    SurfacePro(tonalElevation = tonal.value, modifier = modifier) {

        Row(
            modifier
                .padding(start = dp16)
                .statusBarsPadding()
                .height(Theme.widgetSize.topBarNormalHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.placeholder_user_avatar),
                contentDescription = "",
                modifier = modifier
                    .clip(CircleShape)
                    .size(36.dp)
                    .clickAction(rippleColor = primary) { }
            )
            Spacer(modifier.weight(1f))
            IconAction(
                icon = { AppIcon.Share },
                action = {},
                modifier = modifier
                    .padding(end = dp8)
                    .graphicsLayer {
                        translationX = shareIconTransition.value.toPx()
                    })
        }
    }
}