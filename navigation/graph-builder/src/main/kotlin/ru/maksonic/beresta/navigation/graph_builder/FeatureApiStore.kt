package ru.maksonic.beresta.navigation.graph_builder

import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.splash_screen.api.SplashUiApi

/**
 * @Author maksonic on 15.02.2023
 */
data class FeatureApiStore(
    val splash: SplashUiApi,
    val onboarding: OnboardingApi.Ui,
    val editNote: EditNoteApi.Ui,
    val foldersList: FoldersListApi.Ui
)