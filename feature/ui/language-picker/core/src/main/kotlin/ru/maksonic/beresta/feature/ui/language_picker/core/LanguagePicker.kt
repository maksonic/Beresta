package ru.maksonic.beresta.feature.ui.language_picker.core

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.common.ui_kit.button.radio.RadioButtonEndHint
import ru.maksonic.beresta.common.ui_kit.helpers.PreviewContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp32
import ru.maksonic.beresta.feature.ui.language_picker.api.LanguageUi
import ru.maksonic.beresta.language_engine.shell.provider.AppLangUi
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 28.09.2023
 */
@Composable
internal fun LanguagePicker(
    languages: LanguageUi.Collection,
    currentAppLanguage: AppLangUi,
    onSelectLangClicked: (lang: AppLangUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(bottom = dp32)) {
        languages.data.forEach { item ->
            val translatedLangHint = when (languages.data[item.id].language) {
                AppLangUi.RUSSIAN -> text.translatedLanguage.russian
                AppLangUi.ENGLISH -> text.translatedLanguage.english
                AppLangUi.CHINESE -> text.translatedLanguage.chinese
                AppLangUi.CHINESE_TR -> text.translatedLanguage.chineseTr
            }

            RadioButtonEndHint(
                title = item.language.title,
                hint = translatedLangHint,
                selected = item.language == currentAppLanguage,
                onClick =  { onSelectLangClicked(item.language) }
            )
        }
    }
}

@Preview
@Composable
private fun LanguageItemPreview() {
    PreviewContainer {
        val lang = LanguageUi.Preview
        var selected by remember { mutableStateOf(false) }

        RadioButtonEndHint(
            title = lang.language.title,
            hint = lang.hint,
            selected = selected,
            onClick =  { selected = !selected }
        )
    }
}

@Preview
@Composable
private fun LanguagesListPreview() {
    val current = remember { mutableStateOf(AppLangUi.ENGLISH) }

    PreviewContainer {
        LanguagePicker(
            languages = LanguageUi.Collection.Preview,
            currentAppLanguage = current.value,
            onSelectLangClicked = { current.value = it }
        )
    }
}
