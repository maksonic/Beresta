package ru.maksonic.beresta.feature.onboarding.domain

/**
 * @Author maksonic on 15.12.2022
 */
data class OnboardingEntity(val title: String, val description: String, val image: Int) {
    companion object {
        fun preview() = OnboardingEntity(
            title = "Mock onboarding title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec odio sem, tincidunt a augue eleifend, pellentesque vestibulum tellus.",
            image = 0
        )
    }
}