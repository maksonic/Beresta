package ru.maksonic.beresta.feature.onboarding.core.data

import ru.maksonic.beresta.feature.onboarding.core.R

/**
 * @Author maksonic on 15.02.2023
 */
object OnboardingRepository {
    val onboardings = arrayOf(
        OnboardingEntity(
            title = "First onboarding item title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            image = R.drawable.first
        ),
        OnboardingEntity(
            title = "Second onboarding item title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            image = R.drawable.second
        ),
        OnboardingEntity(
            title = "Third onboarding item title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            image = R.drawable.third
        ),
        OnboardingEntity(
            title = "Синхронизируйте данные с облаком",
            description = "Подключите свой Google аккаунт, чтобы иметь возможность восстановить заметки, а так же использовать их на других устройствах.",
            image = R.drawable.fourth
        ),
    )
}