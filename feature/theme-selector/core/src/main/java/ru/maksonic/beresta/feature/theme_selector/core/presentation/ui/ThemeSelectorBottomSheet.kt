package ru.maksonic.beresta.feature.theme_selector.core.presentation.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorApi
import ru.maksonic.beresta.feature.theme_selector.core.presentation.ThemeSelectorViewModel
import ru.maksonic.beresta.feature.theme_selector.core.presentation.ui.widget.ThemesColumnWidget
import ru.maksonic.beresta.feature.theme_selector.core.presentation.ui.widget.palette_picker.ThemePaletteColorPickerWidget
import ru.maksonic.beresta.ui.theme.color.tertiary
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.button.PrimaryButton
import ru.maksonic.beresta.ui.widget.sheet.BaseBottomDialogSheetWithIndicator

/**
 * @Author maksonic on 20.02.2023
 */
class ThemeSelectorBottomSheet : ThemeSelectorApi.Ui {

    @Composable
    override fun BottomSheet(isVisibleSheet: Boolean, hideSheet: () -> Unit) {
        Content(isVisibleSheet, hideSheet)
    }
}

@Composable
private fun Content(
    isVisibleSheet: Boolean,
    hideSheet: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ThemeSelectorViewModel = koinViewModel()
) {
    val model = viewModel.model.collectAsState().value

    BaseBottomDialogSheetWithIndicator(isVisibleSheet, hideSheet) {
        val scrollState = ScrollState(0)

        Column(
            modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(scrollState)) {

            ThemePaletteColorPickerWidget(
                filledPalettes = model.palettes.filled,
                outlinedPalettes = model.palettes.outlined,
                currentTheme = { model.currentTheme },
                onChangePalette = { item -> viewModel.setThemePalette(item) }
            )

            Divider(
                modifier
                    .fillMaxWidth()
                    .padding(top = dp16, bottom = dp16), color = tertiary)

            ThemesColumnWidget(
                themesCollection = model.themes,
                onChangeTheme = { item -> viewModel.setTheme(item) }
            )

            PrimaryButton(
                action = { hideSheet() },
                title = text.shared.btnTitleSave,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = dp16)
            )
        }
    }
}