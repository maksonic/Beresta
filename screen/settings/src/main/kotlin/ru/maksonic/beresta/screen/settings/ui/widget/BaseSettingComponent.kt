package ru.maksonic.beresta.screen.settings.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.functional.rippleClickable

/**
 * @Author maksonic on 30.04.2023
 */
data class SettingItem(
    val title: String,
    val prefixIcon: ImageVector,
    val postfixHint: String = "",
    val onClick: () -> Unit,
)

data class SettingComponentItem(val title: String, val items: List<SettingItem>)

@Composable
fun BaseSettingComponent(
    settingComponent: SettingComponentItem,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth()) {
        Text(
            text = settingComponent.title,
            style = TextDesign.topBar.copy(color = onPrimary),
            modifier = modifier.padding(dp16)
        )
        settingComponent.items.forEach { BaseClickableItem(it) }
    }
}

@Composable
private fun BaseClickableItem(setting: SettingItem, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.minimumTouchTargetSize)
            .rippleClickable(rippleColor = primary) { setting.onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = setting.prefixIcon,
            tint = onBackground,
            contentDescription = "",
            modifier = modifier.padding(start = dp16)
        )
        Text(
            text = setting.title,
            style = TextDesign.title.copy(color = onBackground),
            modifier = modifier.padding(start = dp16)
        )
        Spacer(modifier.weight(1f))

        Text(
            text = setting.postfixHint,
            style = TextDesign.bodyPrimary.copy(color = outline),
            modifier = modifier.padding(end = dp16)
        )
    }
}