package ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget.pager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryScrollableTabRow
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
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 16.09.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    onTabClicked: (Int) -> Unit
) {
    PrimaryScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = secondaryContainer,
        edgePadding = dp16,
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.PrimaryIndicator(
                    color = onSurface,
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                )
            }
        },
        modifier = modifier.fillMaxWidth()
    ) {
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

val tabs
    @Composable get() = with(text.editor) {
        listOf(
            noteWallpaperCategoryColor,
            noteWallpaperCategoryGradient,
            noteWallpaperCategoryTexture,
            noteWallpaperCategoryImage
        )
    }