package ru.maksonic.beresta.feature.language_picker.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

/**
 * @Author maksonic on 20.06.2023
 */
@Composable
internal fun Container(
    hideSheet: () -> Unit,
    viewModel: LanguagePickerViewModel = koinViewModel()
) {
    val languages = viewModel.languages.collectAsStateWithLifecycle()
    val currentLang = viewModel.currentLangSelection.collectAsStateWithLifecycle()
    Content(
        languages = languages.value,
        currentAppLanguage = currentLang,
        onChangeLangClicked = { viewModel.setLang(it) },
        hideSheet = hideSheet,
    )
}