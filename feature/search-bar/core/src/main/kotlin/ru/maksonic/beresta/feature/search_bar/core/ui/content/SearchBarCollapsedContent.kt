package ru.maksonic.beresta.feature.search_bar.core.ui.content

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Search
import ru.maksonic.beresta.ui.widget.functional.clickAction

/**
 * @Author maksonic on 27.04.2023
 */
@Composable
internal fun SearchBarCollapsedContent(
    onExpandClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(top = dp4)
            .statusBarsPadding()
            .height(Theme.widgetSize.searchBarCollapsedHeight)
            .padding(start = 68.dp, end = dp16)
            .clip(CircleShape)
            .clickAction(tertiaryContainer) { onExpandClicked() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = AppIcon.Search,
            tint = onSurface,
            contentDescription = "",
            modifier = modifier.padding(start = dp12)

        )
        Text(
            text = text.shared.hintFindNote,
            style = TextDesign.bodyPrimary.copy(color = onSurface),
            modifier = modifier.padding(start = dp8)
        )
    }
}