package ru.maksonic.beresta.feature.marker_color_picker.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.secondaryContainer

/**
 * @Author maksonic on 11.09.2023
 */
@Composable
internal fun ColorPickerTabRow(
    selectedTabIndex: Int,
    modifier: Modifier,
    onTabClicked: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = secondaryContainer,
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.SecondaryIndicator(
                    color = onSurface,
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                )
            }
        }) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = index == selectedTabIndex,
                onClick = { onTabClicked(index) },
                selectedContentColor = onSurface,
                unselectedContentColor = outline,
                modifier = Modifier.height(Theme.widgetSize.minimumTouchTargetSize)
            ) {
                Box(modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    Text(title)
                }
            }
        }
    }
}

val tabs
    @Composable get() = with(text.editNote) {
        listOf(
            tabTitleMarkerPickerColorBright,
            tabTitleMarkerPickerColorNormal,
            tabTitleMarkerPickerColorNeutral
        )
    }