package ru.maksonic.beresta.screen.trash_list.notes.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ExpandMore
import ru.maksonic.beresta.ui.theme.icons.RemoveFolder

/**
 * @Author maksonic on 30.05.2023
 */
@Composable
internal fun TrashedFoldersButton(
    modifier: Modifier = Modifier,
    onNavigateToFoldersClicked: () -> Unit
) {
    Box(
        modifier
            .fillMaxWidth()
            .padding(top = dp16, bottom = dp16)
            .height(80.dp)
            .padding(start = dp6, end = dp6)
            .clip(Shape.cornerNormal)
            .background(surfaceVariant)
            .clickable(
                indication = rememberRipple(bounded = true, color = primary),
                interactionSource = remember { MutableInteractionSource() }
            ) { onNavigateToFoldersClicked() },
        contentAlignment = Alignment.Center
    ) {
        Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = AppIcon.RemoveFolder,
                tint = tertiaryContainer,
                contentDescription = "",
                modifier = modifier
                    .padding(start = dp16)
            )

            Text(
                text = text.trash.topBarTitleTrashedFolders,
                style = TextDesign.title.copy(tertiaryContainer),
                modifier = modifier.padding(start = dp16)
            )

            Spacer(modifier.weight(1f))

            Icon(
                imageVector = AppIcon.ExpandMore,
                tint = tertiaryContainer,
                contentDescription = "",
                modifier = modifier
                    .padding(end = dp16)
                    .rotate(-90f)
            )
        }
    }
}