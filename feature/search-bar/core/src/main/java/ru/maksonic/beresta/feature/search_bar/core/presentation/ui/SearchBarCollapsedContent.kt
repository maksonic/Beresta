package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onTertiary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Search

/**
 * @Author maksonic on 24.02.2023
 */
@Composable
internal fun SearchBarCollapsedContent(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.searchBarCollapsedHeight)
            .padding(start = dp12, end = dp12),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = AppIcon.Search,
            tint = onTertiary,
            contentDescription = "",
        )
        Text(
            text = text.shared.hintFindNote,
            style = TextDesign.bodyPrimary.copy(color = onTertiary),
            modifier = modifier.padding(start = dp12)
        )
    }
}