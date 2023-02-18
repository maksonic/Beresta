package ru.maksonic.beresta.feature.language_selector.core.ui

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.language_selector.api.AppLanguage
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi
import ru.maksonic.beresta.feature.language_selector.api.text
import ru.maksonic.beresta.feature.language_selector.core.LanguageSelectorViewModel
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSecondaryContainer
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.tertiary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.PrimaryButton
import ru.maksonic.beresta.ui.widget.button.TertiaryButton
import ru.maksonic.beresta.ui.widget.functional.clickAction

/**
 * @Author maksonic on 16.02.2023
 */
class SelectAppLanguageSheet : LanguageSelectorApi.Ui {

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun BottomSheet(state: () -> ModalBottomSheetState, modifier: Modifier) {
        Content(state, modifier)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Content(
    state: () -> ModalBottomSheetState,
    modifier: Modifier,
    viewModel: LanguageSelectorViewModel = koinViewModel()
) {
    val scope = rememberCoroutineScope()
    val isRussianSelected = rememberSaveable { mutableStateOf(true) }
    val isEnglishSelected = rememberSaveable { mutableStateOf(false) }
    val isChineseSelected = rememberSaveable { mutableStateOf(false) }
val context = LocalContext.current
    BackHandler(state().isVisible) {
        scope.launch {
            state().hide()
        }
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .systemBarsPadding()
            .padding(start = dp16, end = dp16, bottom = dp16)
            .shadow(Theme.elevation.dp16, shape = Theme.shape.cornerExtra)
            .clip(Theme.shape.cornerExtra)
            .background(surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SheetIndicator()
        LanguageItem(
            title = "\uD83C\uDDF7\uD83C\uDDFA  Русский",
            { isRussianSelected.value },
            onChangeLang = {
                viewModel.setLang(AppLanguage.RUSSIAN)
                isRussianSelected.value = true
                isEnglishSelected.value = false
                isChineseSelected.value = false
            })
        LanguageItem(
            title = "\uD83C\uDDEC\uD83C\uDDE7  English",
            { isEnglishSelected.value },
            onChangeLang = {
                viewModel.setLang(AppLanguage.ENGLISH)
                isRussianSelected.value = false
                isEnglishSelected.value = true
                isChineseSelected.value = false
            })

        LanguageItem(
            title = "\uD83C\uDDE8\uD83C\uDDF3  中国人",
            { isChineseSelected.value },
            onChangeLang = {
                viewModel.setLang(AppLanguage.CHINES)
                isRussianSelected.value = false
                isEnglishSelected.value = false
                isChineseSelected.value = true
            })
        Spacer(modifier.height(dp16))
        PrimaryButton(
            action = {
                scope.launch {
                    state().hide()
                }
            }, title = text.languageSheet.saveLang
        )
        Spacer(modifier.height(dp8))
        TertiaryButton(
            action = {
                scope.launch {
                    state().hide()
                }
            },
            title = text.languageSheet.cancel,
        )
        Spacer(modifier.height(dp16))
    }
}

@Composable
private fun SheetIndicator(tint: Color = onSecondaryContainer, modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.minimumTouchTargetSize), contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier
                .padding(top = dp16)
                .clip(CircleShape)
                .size(32.dp, 4.dp)
                .background(tint)
        )
    }
}

@Composable
private fun LanguageItem(
    title: String,
    isSelected: () -> Boolean,
    onChangeLang: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = animateColorAsState(if (isSelected()) tertiary else surface)

    Row(
        modifier
            .padding(start = dp16, end = dp16, bottom = dp8)
            .fillMaxWidth()
            .height(Theme.widgetSize.modalSheetItemHeight)
            .clip(Theme.shape.cornerNormal)
            .clickAction(rippleColor = primary) { onChangeLang() }
            .drawBehind { drawRect(backgroundColor.value) }

        // .border(1.dp, secondary, shape = Theme.shape.cornerNormal)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(title, style = TextDesign.title, modifier = modifier.padding(start = dp8))
    }
}