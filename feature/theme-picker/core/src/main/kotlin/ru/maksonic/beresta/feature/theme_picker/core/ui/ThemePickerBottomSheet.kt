package ru.maksonic.beresta.feature.theme_picker.core.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.feature.theme_picker.core.ThemePickerViewModel
import ru.maksonic.beresta.feature.theme_picker.core.ui.widget.ThemesColumnWidget
import ru.maksonic.beresta.feature.theme_picker.core.ui.widget.palette_picker.ThemePaletteColorPickerWidget
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.onSecondaryContainer
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.button.PrimaryButton

/**
 * @Author maksonic on 22.04.2023
 */
class ThemePickerBottomSheet : ThemePickerApi.Ui {

    @Composable
    override fun BottomSheetContent(hideSheet: () -> Unit) {
        Content(hideSheet)
    }
}

@Composable
private fun Content(
    hideSheet: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ThemePickerViewModel = koinViewModel()
) {
    val uiState = viewModel.model.collectAsStateWithLifecycle()

    Column(
        modifier
            .fillMaxWidth()
            .padding(start = dp16, end = dp16)
            .verticalScroll(ScrollState(0))
    ) {

        ThemePaletteColorPickerWidget(
            uiState = uiState.value,
            onChangePalette = { item -> viewModel.setThemePalette(item) }
        )

        Divider(modifier.padding(top = dp16, bottom = dp16), color = onSecondaryContainer)

        ThemesColumnWidget(
            themesCollection = uiState.value.themes,
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