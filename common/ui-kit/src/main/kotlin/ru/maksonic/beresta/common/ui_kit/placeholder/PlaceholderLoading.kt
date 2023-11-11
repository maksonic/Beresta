package ru.maksonic.beresta.common.ui_kit.placeholder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.surface

/**
 * @Author maksonic on 05.11.2023
 */
@Composable
fun PlaceholderLoading(modifier: Modifier = Modifier, backgroundColor: Color = surface) {
    SurfacePro(modifier = modifier.fillMaxSize(), color = backgroundColor) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = primary)
        }
    }
}