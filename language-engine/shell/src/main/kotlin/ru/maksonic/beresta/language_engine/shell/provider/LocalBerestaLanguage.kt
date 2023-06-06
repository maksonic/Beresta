package ru.maksonic.beresta.language_engine.shell.provider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import ru.maksonic.beresta.language_engine.shell.components.LangEditorData
import ru.maksonic.beresta.language_engine.shell.components.LangFoldersListData
import ru.maksonic.beresta.language_engine.shell.components.LangOnboardingData
import ru.maksonic.beresta.language_engine.shell.components.LangSettingsScreenData
import ru.maksonic.beresta.language_engine.shell.components.LangSharedData
import ru.maksonic.beresta.language_engine.shell.components.LangSortNotesSheetData
import ru.maksonic.beresta.language_engine.shell.components.LangTrashData
import ru.maksonic.beresta.language_engine.shell.components.OnboardingDataItem
import ru.maksonic.beresta.language_engine.shell.components.TranslatedLanguage

/**
 * @Author maksonic on 16.02.2023
 */
private const val ONBOARDINGS_COUNT = 4

enum class AppLanguage(val title: String) {
    RUSSIAN(title = "\uD83C\uDDF7\uD83C\uDDFA  Русский"),
    ENGLISH(title = "\uD83C\uDDEC\uD83C\uDDE7  English"),
    CHINESE(title = "\uD83C\uDDE8\uD83C\uDDF3  简体中文"),
    CHINESE_TR(title = "\uD83C\uDDE8\uD83C\uDDF3  繁体中文"),
}

val LocalBerestaLanguage = staticCompositionLocalOf<BerestaLanguage> {
    error("No language provided")
}

data class BerestaLanguage(
    val langTitle: String = "",
    val translatedLanguage: TranslatedLanguage = TranslatedLanguage(),
    val onboarding: LangOnboardingData = LangOnboardingData(
        data = Array(ONBOARDINGS_COUNT) { OnboardingDataItem() }
    ),
    val shared: LangSharedData = LangSharedData(),
    val settings: LangSettingsScreenData = LangSettingsScreenData(),
    val editNote: LangEditorData = LangEditorData(),
    val folders : LangFoldersListData = LangFoldersListData(),
    val trash: LangTrashData = LangTrashData(),
    val sortNotesSheet: LangSortNotesSheetData = LangSortNotesSheetData()
)

val text: BerestaLanguage @Composable get() = LocalBerestaLanguage.current