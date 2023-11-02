package ru.maksonic.beresta.common.ui_kit.widget.trash_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_kit.icon.IconBase
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Close
import ru.maksonic.beresta.common.ui_kit.icons.Delete
import ru.maksonic.beresta.common.ui_kit.icons.Restart
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onSurface
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp24
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 20.10.2023
 */
@Composable
fun TrashModalSheetDeleteDataContent(
    hideSheet: () -> Unit,
    onRestoreClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Item(AppIcon.Restart, text.shared.btnTitleRestore, onRestoreClicked)
        Item(AppIcon.Delete, text.shared.btnTitleDelete, onDeleteClicked)
        Item(AppIcon.Close, text.shared.btnTitleClose, hideSheet)
        Spacer(modifier.size(dp24))
    }
}

@Composable
fun Item(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
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
        IconBase(icon, tint = tint, modifier = modifier.padding(start = dp16))

        Text(
            text = title,
            style = TextDesign.bodyMedium.copy(color = tint),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.padding(start = dp16)
        )
    }
}