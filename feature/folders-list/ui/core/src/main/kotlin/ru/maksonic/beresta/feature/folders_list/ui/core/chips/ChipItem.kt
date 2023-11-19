package ru.maksonic.beresta.feature.folders_list.ui.core.chips

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.PinFilled
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.colors.onTertiaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp12
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.isDefaultId

/**
 * @Author maksonic on 03.10.2023
 */
@Composable
internal fun ChipItem(
    item: FolderUi,
    isSelected: Boolean,
    onChipClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pinIconTint =
        animateColorAsState(if (isSelected) onTertiaryContainer else onBackground, label = "")

    FilterChip(
        selected = isSelected,
        onClick = onChipClicked,
        label = { Text(item.title) },
        trailingIcon = {
            if (item.isPinned && !item.isDefaultId())
                Icon(
                    imageVector = AppIcon.PinFilled,
                    tint = pinIconTint.value,
                    contentDescription = "",
                    modifier = Modifier.size(dp12)
                )
        },
        shape = Theme.shape.cornerRound,
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = isSelected,
            borderColor = outline
        ),
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = tertiaryContainer,
            labelColor = onBackground,
            selectedLabelColor = onTertiaryContainer
        ),
        modifier = modifier
    )
}