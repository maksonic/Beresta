package ru.maksonic.beresta.feature.edit_note.ui.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.edit_note.ui.core.Model
import ru.maksonic.beresta.feature.edit_note.ui.core.Msg
import ru.maksonic.beresta.feature.edit_note.ui.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.label.LabelOutlined
import ru.maksonic.beresta.ui.widget.button.ClickableIcon
import ru.maksonic.beresta.ui.widget.functional.rippledClick

/**
 * @Author maksonic on 08.09.2023
 */
@Composable
fun CategoryBar(model: State<Model>, send: SendMessage, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(top = Theme.widgetSize.topBarSmallHeight),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier
                .padding(start = dp4)
                .clip(CircleShape)
                .rippledClick(rippleColor = primary) { send(Msg.Ui.OnChangeColorMarkerClicked) }
                .size(Theme.widgetSize.minimumTouchTargetSize)
                .padding(dp12)
                .border(1.dp, onBackground, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier
                    .fillMaxSize()
                    .padding(dp4)
                    .clip(CircleShape)
                    .background(onBackground)
            )
        }
        ClickableIcon(
            icon = AppIcon.LabelOutlined,
            action = {},
        )

        Spacer(modifier.weight(1f))

        DropdownFolderPicker(model, send)
    }
}