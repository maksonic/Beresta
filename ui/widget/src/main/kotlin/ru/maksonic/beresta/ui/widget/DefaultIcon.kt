package ru.maksonic.beresta.ui.widget

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.MaksonicLogo

/**
 * @Author maksonic on 21.07.2023
 */
@Composable
fun DefaultIcon(
    imageVector: ImageVector = AppIcon.MaksonicLogo,
    tint: Color = onBackground,
    contentDescription: String = ""
) {
    Icon(imageVector = imageVector, tint = tint, contentDescription = contentDescription)
}