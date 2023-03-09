package ru.maksonic.beresta.ui.widget.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.primaryContainer

/**
 * @Author maksonic on 09.03.2023
 */
@Composable
fun IconActionCircleOverflow(
    icon: ImageVector,
    onBtnClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithScaleInOutOnClick(
        onClick = { onBtnClicked() },
        modifier = modifier
            .statusBarsPadding()
            .size(Theme.widgetSize.minimumTouchTargetSize)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = primaryContainer),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = modifier.size(36.dp),
        ) {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(imageVector = icon, contentDescription = "", tint = onBackground)
            }
        }
    }
}