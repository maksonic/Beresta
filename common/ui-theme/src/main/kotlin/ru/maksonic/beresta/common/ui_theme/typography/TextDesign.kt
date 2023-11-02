package ru.maksonic.beresta.common.ui_theme.typography

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.common.ui_theme.colors.onBackground

/**
 * @Author maksonic on 08.11.2022
 */
object TextDesign {
    val bodyLarge @Composable get() = MaterialTheme.typography.bodyLarge.copy(onBackground)
    val bodyMedium @Composable get() = MaterialTheme.typography.bodyMedium.copy(onBackground)
    val bodySmall @Composable get() = MaterialTheme.typography.bodySmall.copy(onBackground)

    val displayLarge @Composable get() = MaterialTheme.typography.displayLarge.copy(onBackground)
    val displayMedium @Composable get() = MaterialTheme.typography.displayMedium.copy(onBackground)
    val displaySmall @Composable get() = MaterialTheme.typography.bodySmall.copy(onBackground)

    val headlineLarge @Composable get() = MaterialTheme.typography.headlineLarge.copy(onBackground)
    val headlineMedium @Composable get() = MaterialTheme.typography.headlineMedium.copy(onBackground)
    val headlineSmall @Composable get() = MaterialTheme.typography.headlineSmall.copy(onBackground)

    val labelLarge @Composable get() = MaterialTheme.typography.labelLarge.copy(onBackground)
    val labelMedium @Composable get() = MaterialTheme.typography.labelMedium.copy(onBackground)
    val labelSmall @Composable get() = MaterialTheme.typography.labelSmall.copy(onBackground)

    val titleLarge @Composable get() = MaterialTheme.typography.titleLarge.copy(onBackground)
    val titleMedium @Composable get() = MaterialTheme.typography.titleMedium.copy(onBackground)
    val titleSmall @Composable get() = MaterialTheme.typography.titleSmall.copy(onBackground)
}