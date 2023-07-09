package ru.maksonic.beresta.feature.onboarding.ui.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

/**
 * @Author maksonic on 24.04.2023
 */
@Stable
@Immutable
data class OnboardingUi(val title: String, val description: String) {
    companion object {
        private const val description =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Curabitur rhoncus odio vel dolor ullamcorper lacinia" +
                    " nec ac sapien. Aliquam non laoreet lectus, vitae volutpat risus." +
                    " Vivamus pretium gravida erat sit amet tempus. " +
                    "Donec vel augue ac arcu porttitor facilisis sit amet quis orci."

        val Preview = OnboardingUi("Onboarding preview title.", description = description)
    }
}