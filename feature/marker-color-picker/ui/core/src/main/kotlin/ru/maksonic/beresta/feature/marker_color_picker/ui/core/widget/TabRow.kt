package ru.maksonic.beresta.feature.marker_color_picker.ui.core.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onSurface
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 11.09.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ColorPickerTabRow(
    selectedTabIndex: Int,
    modifier: Modifier,
    onTabClicked: (Int) -> Unit
) {
    SecondaryTabRow(
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
                modifier = Modifier.height(Theme.size.minimumTouchTargetSize)
            ) {
                Box(modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    Text(title)
                }
            }
        }
    }
}

private val tabs
    @Composable get() = with(text.editor) {
        listOf(
            tabTitleMarkerPickerColorBright,
            tabTitleMarkerPickerColorNormal,
            tabTitleMarkerPickerColorNeutral
        )
    }