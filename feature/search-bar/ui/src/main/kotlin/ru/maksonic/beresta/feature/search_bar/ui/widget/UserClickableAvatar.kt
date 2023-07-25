package ru.maksonic.beresta.feature.search_bar.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import ru.maksonic.beresta.core.R
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp48

/**
 * @Author maksonic on 23.07.2023
 */
@Composable
internal fun UserClickableAvatar(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.placeholder_user_avatar),
        contentDescription = "",
        modifier = modifier
            .padding(end = dp4)
            .size(dp48)
            .clip(CircleShape)
            .clickable(
                role = Role.Button,
                indication = rememberRipple(color = primary, bounded = true),
                interactionSource = remember { MutableInteractionSource() }) { onClick() }
            .padding(dp4)
    )
}