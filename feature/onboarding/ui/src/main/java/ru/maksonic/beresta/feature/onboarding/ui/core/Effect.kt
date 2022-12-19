package ru.maksonic.beresta.feature.onboarding.ui.core

import ru.maksonic.beresta.elm.ElmEffect

/**
 * @Author maksonic on 15.12.2022
 */
sealed class Effect : ElmEffect {
    object SlideNextPage : Effect()
}