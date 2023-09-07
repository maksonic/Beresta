package ru.maksonic.beresta.ui.widget.bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSnack
import ru.maksonic.beresta.ui.theme.color.onSnackContainer
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.snack
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Close
import ru.maksonic.beresta.ui.widget.functional.noRippleClick
import ru.maksonic.beresta.ui.widget.functional.rippledClick
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

/**
 * @Author maksonic on 05.09.2023
 */
const val SNACK_MESSAGE_MAX_LINES = 3

@Composable
fun SnackBar(snackBarData: SnackbarData?, modifier: Modifier = Modifier) {
    SurfacePro(
        color = snack,
        shape = Shape.cornerNormal,
        modifier = modifier
            .fillMaxWidth()
            .padding(dp8)
            .defaultMinSize(minHeight = Theme.widgetSize.minimumTouchTargetSize)
    ) {
        Row(
            modifier = modifier.padding(dp8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = snackBarData?.visuals?.message ?: "",
                style = TextDesign.bodyPrimary.copy(onSnackContainer),
                maxLines = SNACK_MESSAGE_MAX_LINES,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .weight(1f)
                    .padding(end = dp8)
            )

            Box(
                modifier
                    .height(Theme.widgetSize.chipHeight)
                    .clip(Shape.cornerNormal)
                    .rippledClick(
                        rippleColor = primary,
                        onClick = { snackBarData?.performAction() }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text.shared.btnTitleCancel,
                    style = TextDesign.captionNormal.copy(onSnack),
                    modifier = modifier.padding(start = dp16, end = dp16)
                )
            }

            Icon(
                imageVector = AppIcon.Close,
                tint = onSnackContainer,
                contentDescription = "",
                modifier = modifier
                    .size(Theme.widgetSize.chipHeight)
                    .noRippleClick { snackBarData?.dismiss() }
                    .padding(dp6)
            )
        }
    }
}