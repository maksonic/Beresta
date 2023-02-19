package ru.maksonic.beresta.feature.language_selector.core.ui

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
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.language_selector.core.LanguageSelectorViewModel
import ru.maksonic.beresta.feature.language_selector.core.LanguageUi
import ru.maksonic.beresta.feature.language_selector.core.LanguagesCollection
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.tertiary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.PrimaryButton
import ru.maksonic.beresta.ui.widget.functional.clickAction

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
        Content(isVisibleSheet, hideSheet, modifier)
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
        PrimaryButton(action = { hideSheet() }, title = text.languageSheetTextData.btnTitleSaveLanguage)
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
            LanguageItem(
                item = item,
                onChangeLang = { onChangeLang(item) }
            )
        }
    }
}

@Composable
private fun LanguageItem(
    item: LanguageUi,
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
    }
}
