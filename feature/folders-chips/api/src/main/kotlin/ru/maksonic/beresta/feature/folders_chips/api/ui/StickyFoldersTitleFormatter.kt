package ru.maksonic.beresta.feature.folders_chips.api.ui

import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage

/**
 * @Author maksonic on 04.07.2023
 */
interface StickyFoldersTitleFormatter {
    fun format(folder: FolderUi, currentLang: AppLanguage): String
}