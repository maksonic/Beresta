package ru.maksonic.beresta.feature.theme_selector.core.presentation.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
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
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorApi
import ru.maksonic.beresta.feature.theme_selector.api.ThemeUi
import ru.maksonic.beresta.feature.theme_selector.api.ThemesCollection
import ru.maksonic.beresta.feature.theme_selector.core.presentation.ThemeSelectorViewModel
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onTertiary
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
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

        ThemesUiItems(
            themesCollection = themes,
            onChangeLang = { item -> viewModel.setTheme(item.theme) }
        )

        PrimaryButton(
            action = { hideSheet() },
            title = text.shared.btnTitleSave,
            modifier = modifier.padding(bottom = dp16)
        )
    }
}

@Composable
private fun ThemesUiItems(
    themesCollection: ThemesCollection,
    onChangeLang: (item: ThemeUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(bottom = dp16)) {
        themesCollection.data.forEach { item ->
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
    val backgroundColor = animateColorAsState(if (item.isSelected) surfaceVariant else surface)

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
