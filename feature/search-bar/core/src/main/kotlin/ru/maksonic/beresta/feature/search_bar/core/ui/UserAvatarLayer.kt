package ru.maksonic.beresta.feature.search_bar.core.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListSharedUiState
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.functional.clickAction

/**
 * @Author maksonic on 26.04.2023
 */
@Composable
internal fun UserAvatarLayer(
    notesListSharedUiState: State<NotesListSharedUiState>,
    modifier: Modifier = Modifier
) {
    val tonal = animateDpAsState(
        if (notesListSharedUiState.value.isNotColoredTopBar) Theme.tonal.Level0
        else Theme.tonal.Level3, label = "", animationSpec = tween(Theme.animSpeed.common)
    )

    val iconScale = animateFloatAsState(
        if (notesListSharedUiState.value.isSelectionState) 0f
        else 1f, tween(Theme.animSpeed.common), label = ""
    )

    SurfacePro(
        tonalElevation = tonal.value,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .height(Theme.widgetSize.topBarNormalHeight)
                .padding(start = dp16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.placeholder_user_avatar),
                contentDescription = "",
                modifier = modifier
                    .clip(CircleShape)
                    .size(36.dp)
                    .graphicsLayer {
                        scaleX = iconScale.value
                        scaleY = iconScale.value
                    }
                    .clickAction(rippleColor = primary) { }
            )
        }
    }
}