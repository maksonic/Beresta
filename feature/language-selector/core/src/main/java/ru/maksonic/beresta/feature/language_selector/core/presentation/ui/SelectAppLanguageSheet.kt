package ru.maksonic.beresta.feature.language_selector.core.presentation.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi
import ru.maksonic.beresta.feature.language_selector.api.LanguageUi
import ru.maksonic.beresta.feature.language_selector.api.LanguagesCollection
import ru.maksonic.beresta.feature.language_selector.api.provider.AppLanguage
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.language_selector.core.presentation.LanguageSelectorViewModel
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.PrimaryButton
import ru.maksonic.beresta.ui.widget.functional.clickAction
import ru.maksonic.beresta.ui.widget.sheet.BaseBottomDialogSheetWithIndicator

/**
 * @Author maksonic on 16.02.2023
 */
class SelectAppLanguageSheet : LanguageSelectorApi.Ui {

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
    viewModel: LanguageSelectorViewModel = koinViewModel()
) {
    val languages = viewModel.languages.collectAsState().value

    BaseBottomDialogSheetWithIndicator(isVisibleSheet, hideSheet) {

        LanguagesUiItems(
            languagesCollection = languages,
            onChangeLang = { item -> viewModel.setLang(item.language) }
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
private fun LanguagesUiItems(
    languagesCollection: LanguagesCollection,
    onChangeLang: (item: LanguageUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(bottom = dp16)) {
        languagesCollection.data.forEach { item ->
            val translatedLangHint = when (languagesCollection.data[item.id].language) {
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
    val backgroundColor = if (item.isSelected) onSurface else transparent

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
            style = TextDesign.title.copy(color = onTertiary),
            modifier = modifier.padding(start = dp8)
        )
        Spacer(modifier.weight(1f))
        Text(
            text = translatedLangHint,
            style = TextDesign.bodySecondary.copy(color = onTertiary),
            modifier = modifier.padding(end = dp8)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectAppLanguageSheetPreview() {
    BerestaTheme {
        Content(
            isVisibleSheet = true,
            hideSheet = { },
            Modifier
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun LangItemPreview() {
    BerestaTheme {
        LanguageItem(
            item = LanguageUi(0, AppLanguage.RUSSIAN),
            onChangeLang = { },
            translatedLangHint = "Russian"
        )
    }
}
