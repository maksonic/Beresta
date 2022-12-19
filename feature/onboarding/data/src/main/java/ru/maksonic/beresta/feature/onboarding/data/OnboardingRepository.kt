package ru.maksonic.beresta.feature.onboarding.data

import ru.maksonic.beresta.feature.onboarding.domain.Repository

/**
 * @Author maksonic on 15.12.2022
 */
object OnboardingRepository : Repository {
    override val onboardings = arrayOf(
        ru.maksonic.beresta.feature.onboarding.domain.OnboardingEntity(
            title = "First onboarding item title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            image = R.drawable.first
        ),
        ru.maksonic.beresta.feature.onboarding.domain.OnboardingEntity(
            title = "Second onboarding item title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            image = R.drawable.second
        ),
        ru.maksonic.beresta.feature.onboarding.domain.OnboardingEntity(
            title = "Third onboarding item title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            image = R.drawable.third
        ),
        ru.maksonic.beresta.feature.onboarding.domain.OnboardingEntity(
            title = "Синхронизируйте данные с облаком",
            description = "Подключите свой Google аккаунт, чтобы иметь возможность восстановить заметки, а так же использовать их на других устройствах.",
            image = R.drawable.fourth
        ),
    )

}