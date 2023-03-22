package ru.maksonic.beresta.feature.theme_selector.core.presentation.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorUiApi
import ru.maksonic.beresta.feature.theme_selector.core.presentation.ThemeSelectorViewModel
import ru.maksonic.beresta.feature.theme_selector.core.presentation.ui.widget.ThemesColumnWidget
import ru.maksonic.beresta.feature.theme_selector.core.presentation.ui.widget.palette_picker.ThemePaletteColorPickerWidget
import ru.maksonic.beresta.ui.theme.color.onSecondaryContainer
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.button.PrimaryButton
import ru.maksonic.beresta.ui.widget.sheet.BaseBottomDialogSheetWithIndicator

/**
 * @Author maksonic on 20.02.2023
 */
class ThemeSelectorBottomSheet : ThemeSelectorUiApi {

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
    val model = viewModel.model.collectAsStateWithLifecycle().value

    BaseBottomDialogSheetWithIndicator(isVisibleSheet, hideSheet) {

        Column(
            modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(ScrollState(0))) {

            ThemePaletteColorPickerWidget(
                filledPalettes = model.palettes.filled,
                outlinedPalettes = model.palettes.outlined,
                currentTheme = { model.currentTheme },
                onChangePalette = { item -> viewModel.setThemePalette(item) }
            )

            Divider(modifier.padding(top = dp16, bottom = dp16), color = onSecondaryContainer)

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