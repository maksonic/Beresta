package ru.maksonic.beresta.feature.onboarding.ui.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingEntity

/**
 * @Author maksonic on 15.12.2022
 */
@Stable
@Immutable
data class Model(
    val onboardings: Array<OnboardingEntity> = emptyArray()
) : ElmModel {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Model

        if (!onboardings.contentEquals(other.onboardings)) return false

        return true
    }

    override fun hashCode(): Int {
        return onboardings.contentHashCode()
    }
}