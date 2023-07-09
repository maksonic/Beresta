package ru.maksonic.beresta.feature.theme_picker.ui.widget.palette_picker

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Done
import ru.maksonic.beresta.ui.widget.functional.tintSimply

/**
 * @Author maksonic on 28.02.2023
 */
@Composable
internal fun CheckMark(
    isCurrent: Boolean,
    checkColor: State<Color>,
    scope: BoxWithConstraintsScope,
    modifier: Modifier = Modifier
) {
    val labelSize = animateDpAsState(if (isCurrent) scope.maxHeight else 0.dp, label = "")

    Icon(
        imageVector = AppIcon.Done,
        contentDescription = "",
        modifier = modifier.size(labelSize.value).tintSimply(checkColor)
    )
}