package ru.maksonic.beresta.feature.theme_selector.core.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.theme_selector.api.ThemesCollection
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorApi
import ru.maksonic.beresta.feature.theme_selector.api.ThemeUi
import ru.maksonic.beresta.feature.theme_selector.core.ThemeSelectorViewModel
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onPrimaryContainer
import ru.maksonic.beresta.ui.theme.color.onTertiary
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.secondary
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.tertiary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.PrimaryButton
import ru.maksonic.beresta.ui.widget.functional.clickAction
import ru.maksonic.beresta.ui.widget.sheet.BaseBottomDialogSheetWithIndicator

/**
 * @Author maksonic on 20.02.2023
 */
class ThemeSelectorBottomSheet : ThemeSelectorApi.Ui {

    @Composable
    override fun BottomSheet(
        isVisibleSheet: Boolean,
        hideSheet: () -> Unit,
        modifier: Modifier
    ) {
        Content(isVisibleSheet, hideSheet, modifier = modifier)
    }
}

@Composable
private fun Content(
    isVisibleSheet: Boolean,
    hideSheet: () -> Unit,
    modifier: Modifier,
    viewModel: ThemeSelectorViewModel = koinViewModel()
) {
    val themes = viewModel.themes.collectAsState().value

    BaseBottomDialogSheetWithIndicator(isVisibleSheet, hideSheet) {

        ThemesUiItems(themesCollection = themes) { item ->
            viewModel.setTheme(item.theme)
        }

        Spacer(modifier.height(dp16))
        PrimaryButton(
            action = { hideSheet() },
            title = text.langSharedData.btnTitleClose
        )
        Spacer(modifier.height(dp16))
    }
}

@Composable
private fun ThemesUiItems(
    themesCollection: ThemesCollection,
    onChangeLang: (item: ThemeUi) -> Unit
) {
    LazyColumn() {
        items(themesCollection.data, key = { theme -> theme.id }) { item ->
            val title = when (item.theme) {
                AppTheme.SYSTEM -> text.settings.titleThemeSystem
                AppTheme.LIGHT -> text.settings.titleThemeLight
                AppTheme.DARK -> text.settings.themeTitleNight
                AppTheme.HIGH_CONTRAST -> text.settings.themeTitleHighContrast
            }
            val updated = item.copy(title = title)
            ThemeItem(
                item = updated,
                onChangeLang = { onChangeLang(item) }
            )
        }
    }
}

@Composable
private fun ThemeItem(
    item: ThemeUi,
    onChangeLang: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = animateColorAsState(if (item.isSelected) tertiary else surface)

    Row(
        modifier
            .padding(start = dp16, end = dp16, bottom = dp8)
            .fillMaxWidth()
            .height(Theme.widgetSize.modalSheetItemHeight)
            .clip(Theme.shape.cornerNormal)
            .clickAction(rippleColor = primary) { onChangeLang() }
            .drawBehind { drawRect(backgroundColor.value) },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = item.icon,
            tint = onTertiary,
            contentDescription = "",
            modifier = modifier.padding(start = dp8)
        )

        Text(
            text = item.title,
            style = TextDesign.title,
            modifier = modifier.padding(start = dp8)
        )
    }
}