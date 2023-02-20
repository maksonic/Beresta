package ru.maksonic.beresta.feature.onboarding.core.presentation.ui

/**
 * @Author maksonic on 20.02.2023
 */
data class OnboardingUi(
    val title: String,
    val description: String,
    val imageId: Int
) {
    companion object {
        private const val description =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Curabitur rhoncus odio vel dolor ullamcorper lacinia" +
                    " nec ac sapien. Aliquam non laoreet lectus, vitae volutpat risus." +
                    " Vivamus pretium gravida erat sit amet tempus. " +
                    "Donec vel augue ac arcu porttitor facilisis sit amet quis orci."

        val preview = OnboardingUi(
            "Onboarding preview title.",
            description = description,
            imageId = ru.maksonic.beresta.ui.theme.R.drawable.logo_bottom_maksonic_day

        )
    }
}