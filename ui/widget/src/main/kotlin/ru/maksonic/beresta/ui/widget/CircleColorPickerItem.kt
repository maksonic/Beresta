package ru.maksonic.beresta.ui.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.ui.theme.component.dp2
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Done
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.tintSimply

/**
 * @Author maksonic on 10.09.2023
 */
@Composable
fun CircleColorPickerItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    backgroundColor: Color,
    borderColor: Color = backgroundColor,
    checkMarkColor: Color,
    modifier: Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        BoxWithScaleInOutOnClick(onClick = onClick) {
            BoxWithConstraints(
                modifier
                    .fillMaxHeight(1f)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(dp2, borderColor, CircleShape)
                    .drawBehind { drawRect(backgroundColor) },
                contentAlignment = Alignment.Center
            ) {
                AnimateFadeInOut(isSelected) {
                    CheckMark(
                        scope = this,
                        checkMarkColor = checkMarkColor
                    )
                }
            }
        }
    }
}

@Composable
internal fun CheckMark(
    checkMarkColor: Color,
    scope: BoxWithConstraintsScope,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = AppIcon.Done,
        contentDescription = "",
        modifier = modifier
            .size(scope.maxHeight)
            .tintSimply(checkMarkColor)
    )
}