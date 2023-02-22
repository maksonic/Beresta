package ru.maksonic.beresta.feature.language_selector.core.presentation.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.secondary
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.tertiary
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
    viewModel: LanguageSelectorViewModel = koinViewModel()
) {
    val languages = viewModel.languages.collectAsState().value

    BaseBottomDialogSheetWithIndicator(isVisibleSheet, hideSheet) {

        LanguagesUiItems(languagesCollection = { languages }) { item ->
            viewModel.setLang(item.language)
        }

        Spacer(modifier.height(dp16))

        PrimaryButton(
            action = { hideSheet() },
            title = text.shared.btnTitleSave
        )
        Spacer(modifier.height(dp16))
    }
}

@Composable
fun LanguagesUiItems(
    languagesCollection: () -> LanguagesCollection,
    onChangeLang: (item: LanguageUi) -> Unit
) {

    LazyColumn() {
        items(languagesCollection().data, key = { lang -> lang.id }) { item ->
            val translatedLangHint = when (languagesCollection().data[item.id].language) {
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
    val backgroundColor = animateColorAsState(if (item.isSelected) tertiary else surface)

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

        Text(
            item.language.title,
            style = TextDesign.title,
            modifier = modifier.padding(start = dp8)
        )
        Spacer(modifier.weight(1f))
        Text(
            text = translatedLangHint,
            style = TextDesign.captionNormal.copy(color = secondary),
            modifier = modifier.padding(end = dp8)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SelectAppLanguageSheetPreview() {
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
