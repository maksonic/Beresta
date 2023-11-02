package ru.maksonic.beresta.navigation.graph_builder

import ru.maksonic.beresta.feature.onboarding.ui.api.OnboardingUiApi
import ru.maksonic.beresta.feature.ui.edit_note.api.EditNoteUiApi

/**
 * @Author maksonic on 04.10.2023
 */
data class FeatureApiContainer(
    val onboarding: OnboardingUiApi,
    val editNote: EditNoteUiApi
)