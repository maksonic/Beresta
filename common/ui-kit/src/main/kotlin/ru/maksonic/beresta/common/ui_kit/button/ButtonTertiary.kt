package ru.maksonic.beresta.common.ui_kit.button

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import ru.maksonic.beresta.common.ui_kit.icon.IconBase
import ru.maksonic.beresta.common.ui_theme.colors.onTertiaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.PrimaryRipple
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign

/**
 * @Author maksonic on 05.11.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonTertiary(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    title: String,
    contentTint: Color = onTertiaryContainer
) {
    CompositionLocalProvider(LocalRippleTheme provides PrimaryRipple) {
        FilterChip(
            selected = true,
            enabled = true,
            onClick = onClick,
            label = { Text(title, style = TextDesign.labelMedium.copy(contentTint)) },
            shape = CircleShape,
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = tertiaryContainer,
                selectedLabelColor = onTertiaryContainer
            ),
            trailingIcon = { icon?.let { IconBase(it, tint = contentTint) } },
            modifier = modifier
        )
    }
}