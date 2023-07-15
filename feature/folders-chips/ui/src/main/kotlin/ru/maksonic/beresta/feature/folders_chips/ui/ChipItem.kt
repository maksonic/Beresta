package ru.maksonic.beresta.feature.folders_chips.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.api.ui.isDefaultId
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.PinFilled

/**
 * @Author maksonic on 03.07.2023
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChipItem(
    item: FolderUi,
    isSelected: Boolean,
    onChipClicked: (id: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val pinIconTint =
        animateColorAsState(if (isSelected) onTertiaryContainer else onBackground, label = "")

    FilterChip(
        selected = isSelected,
        onClick = { onChipClicked(item.id) },
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
        shape = Shape.cornerRound,
        border = FilterChipDefaults.filterChipBorder(borderColor = outline),
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = tertiaryContainer,
            labelColor = onBackground,
            selectedLabelColor = onTertiaryContainer
        ),
        modifier = modifier
    )
}