package ru.maksonic.beresta.feature.ui.language_picker.core

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_kit.text.TextHeadlineSmall
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.feature.ui.language_picker.api.LanguageUi
import ru.maksonic.beresta.language_engine.shell.provider.AppLangUi
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 28.09.2023
 */
@Composable
internal fun Content(
    languages: LanguageUi.Collection,
    currentLang: AppLangUi,
    onSelectLangClicked: (AppLangUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextHeadlineSmall(
            text = text.settings.dialogLanguagePickerTitle,
            modifier = modifier.padding(bottom = dp16)
        )

        LanguagePicker(
            languages = languages,
            currentAppLanguage = currentLang,
            onSelectLangClicked = onSelectLangClicked
        )
    }
}
