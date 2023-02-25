package ru.maksonic.beresta.ui.widget

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.color.onTertiary
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.UserAccount
import ru.maksonic.beresta.ui.widget.functional.clickAction

/**
 * @Author maksonic on 21.02.2023
 */
@Composable
fun UserAccountCircleAvatarTopBarWidget(modifier: Modifier = Modifier) {
    Icon(
        imageVector = AppIcon.UserAccount,
        tint = onTertiary,
        contentDescription = "",
        modifier = modifier
            .clip(CircleShape)
            .size(36.dp)
            .clickAction(rippleColor = primary) { }
    )
}