package ru.maksonic.beresta.navigation.graph_builder

import ru.maksonic.beresta.feature.folders_list.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.splash_screen.api.SplashApi

/**
 * @Author maksonic on 15.02.2023
 */
data class FeatureApiStore(
    val splash: SplashApi,
    val onboarding: OnboardingApi.Ui,
    val foldersList: FoldersListApi.Ui
)