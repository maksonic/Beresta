package ru.maksonic.beresta.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_theme.Theme

/**
 * @Author maksonic on 27.09.2023
 */
@Composable
internal fun Content(modifier: Modifier = Modifier) {

    val lightModeColor = Color(0xFFFFFFFF)
    val darkModeColor = Color(0xFF212121)
    val splashBackgroundColor = rememberUpdatedState(
        if (Theme.darkMode.value) darkModeColor else lightModeColor
    )

    Box(
        modifier
            .fillMaxSize()
            .drawBehind { drawRect(splashBackgroundColor.value) },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Theme.image.splashCenterLogo),
            contentDescription = "",
            modifier = modifier.size(300.dp),
        )

        Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Image(
                painter = painterResource(Theme.image.splashBottomLogo),
                contentDescription = "",
                modifier = modifier
                    .padding(bottom = 60.dp)
                    .size(200.dp, 80.dp)
            )
        }
    }
}