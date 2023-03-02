package ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.filter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import ru.maksonic.beresta.feature.notes_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.notes_list.core.presentation.Msg
import ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick

/**
 * @Author maksonic on 02.03.2023
 */
@Composable
internal fun FilterChipItem(
    item: FilterChipUi,
    isVisibleFirstNote: () -> Boolean,
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    val titleColor = if (item.isSelected) onPrimary else onBackground
    val backgroundColor = if (item.isSelected) primary else {
        if (isVisibleFirstNote()) surfaceVariant else onTertiaryContainer
    }

    BoxWithScaleInOutOnClick(onClick = { send(Msg.Ui.OnChipFilterClicked(item.id)) }) {

        Box(
            modifier
                .height(Theme.widgetSize.filterChipHeight)
                .clip(CircleShape)
                .drawBehind { drawRect(backgroundColor) },
            contentAlignment = Alignment.Center

        ) {
            Text(
                item.title,
                style = TextDesign.bodyPrimaryMedium.copy(color = titleColor),
                modifier = modifier.padding(start = dp12, end = dp12)
            )
        }
    }
}
