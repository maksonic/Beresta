package ru.maksonic.beresta.feature.ui.language_picker.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.ui.language_picker.core.main.LanguagePickerViewModel

/**
 * @Author maksonic on 28.09.2023
 */
@Composable
internal fun Container(viewModel: LanguagePickerViewModel = koinViewModel()) {
    val languages by viewModel.languages.collectAsStateWithLifecycle()
    val currentLang by viewModel.currentLangSelection.collectAsStateWithLifecycle()

    Content(
        languages = languages,
        currentLang = currentLang,
        onSelectLangClicked = { viewModel.setLang(it) }
    )
}