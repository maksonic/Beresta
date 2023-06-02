package ru.maksonic.beresta.feature.language_picker.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.language_picker.api.LanguagePickerApi
import ru.maksonic.beresta.feature.language_picker.api.LanguageUi
import ru.maksonic.beresta.feature.language_picker.core.LanguagePickerViewModel
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.inverseSurface
import ru.maksonic.beresta.ui.theme.color.onSecondary
import ru.maksonic.beresta.ui.theme.color.onSecondaryContainer
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.PrimaryButton
import ru.maksonic.beresta.ui.widget.functional.clickAction

/**
 * @Author maksonic on 22.04.2023
 */
class LanguagePickerBottomSheetBottomSheet : LanguagePickerApi.Ui {

    @Composable
    override fun BottomSheetContent(hideSheet: () -> Unit) {
        Content(hideSheet)
    }
}

@Composable
private fun Content(
    hideSheet: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LanguagePickerViewModel = koinViewModel()
) {
    val languages = viewModel.languages.collectAsStateWithLifecycle()

   Column(modifier.padding(start = dp16, end = dp16)) {
       LanguagesList(
           languages = languages.value, onChangeLang = { item -> viewModel.setLang(item.language) }
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

@Composable
private fun LanguagesList(
    languages: LanguageUi.Collection,
    onChangeLang: (item: LanguageUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(bottom = dp16)) {
        languages.data.forEach { item ->
            val translatedLangHint = when (languages.data[item.id].language) {
                AppLanguage.RUSSIAN -> text.translatedLanguage.russian
                AppLanguage.ENGLISH -> text.translatedLanguage.english
                AppLanguage.CHINESE -> text.translatedLanguage.chinese
                AppLanguage.CHINESE_TR -> text.translatedLanguage.chineseTr
            }
            LanguageItem(
                item = item,
                translatedLangHint = translatedLangHint,
                onChangeLang = { onChangeLang(item) }
            )
        }
    }
}

@Composable
private fun LanguageItem(
    item: LanguageUi,
    translatedLangHint: String,
    onChangeLang: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (item.isSelected) onSecondaryContainer else transparent

    Row(
        modifier
            .padding(bottom = dp8)
            .fillMaxWidth()
            .height(Theme.widgetSize.modalSheetItemHeight)
            .clip(Theme.shape.cornerNormal)
            .clickAction(rippleColor = primary) { onChangeLang() }
            .drawBehind { drawRect(backgroundColor) },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            item.language.title,
            style = TextDesign.title.copy(color = onSecondary),
            modifier = modifier.padding(start = dp8)
        )
        Spacer(modifier.weight(1f))
        Text(
            text = translatedLangHint,
            style = TextDesign.bodySecondary.copy(color = inverseSurface),
            modifier = modifier.padding(end = dp8)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectAppLanguageSheetPreview() {
    BerestaTheme {
        Content(hideSheet = { }, Modifier)
    }
}

@Preview(showBackground = true)
@Composable
private fun LangItemPreview() {
    BerestaTheme {
        LanguageItem(
            item = LanguageUi.Preview,
            onChangeLang = { },
            translatedLangHint = "Russian"
        )
    }
}