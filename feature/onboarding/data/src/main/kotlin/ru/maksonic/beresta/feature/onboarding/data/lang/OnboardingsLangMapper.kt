package ru.maksonic.beresta.feature.onboarding.data.lang

import ru.maksonic.beresta.feature.onboarding.domain.Onboarding

/**
 * @Author maksonic on 12.10.2023
 */
fun List<OnboardingItemModel>.toUi() = this.map { Onboarding(
    id = it.id, title = it.title, description = it.description, image = 0
) }