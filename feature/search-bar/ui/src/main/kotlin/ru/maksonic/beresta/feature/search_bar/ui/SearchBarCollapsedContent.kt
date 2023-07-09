package ru.maksonic.beresta.feature.search_bar.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.GridView
import ru.maksonic.beresta.ui.theme.icons.ListView
import ru.maksonic.beresta.ui.theme.icons.Search
import ru.maksonic.beresta.ui.widget.button.ClickableIcon
import ru.maksonic.beresta.ui.widget.functional.clickAction

/**
 * @Author maksonic on 27.04.2023
 */
@Composable
internal fun SearchBarCollapsedContent(
    onExpandClicked: () -> Unit,
    onChangeGridClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val icon = remember { mutableStateOf(AppIcon.ListView) }
    val gridCount = rememberUpdatedState(listUiSortState.gridCount)
    LaunchedEffect(listUiSortState.gridCount) {
        icon.value = if (gridCount.value == 1) AppIcon.ListView else AppIcon.GridView
    }

    Row(
        modifier
            .fillMaxWidth()
            .padding(top = dp4)
            .statusBarsPadding()
            .height(Theme.widgetSize.searchBarCollapsedHeight)
            .padding(start = 68.dp, end = dp16)
            .clip(CircleShape)
            .clickAction(primary) { onExpandClicked() },
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
        Spacer(modifier.weight(1f))
        ClickableIcon(icon = icon.value, tint = onSurface) { onChangeGridClicked() }
    }
}