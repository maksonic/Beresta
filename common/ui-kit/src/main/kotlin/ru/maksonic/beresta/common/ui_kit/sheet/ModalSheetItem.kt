package ru.maksonic.beresta.common.ui_kit.sheet

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_kit.icon.IconBase
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onSurface
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign

/**
 * @Author maksonic on 13.11.2023
 */
@Composable
fun ModalSheetItem(
    icon: ImageVector? = null,
    title: String,
    onClick: () -> Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    tint: Color = onSurface,
    rippleColor: Color = primary
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.size.modalSheetItemHeightNormal)
            .rippledClick(rippleColor = rippleColor, onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            IconBase(it, tint = tint, modifier = modifier.padding(start = dp16))
        }

        Text(
            text = title,
            style = TextDesign.bodyMedium.copy(color = tint),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.padding(start = dp16)
        )
    }
}