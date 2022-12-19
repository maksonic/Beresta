package ru.maksonic.beresta.navigation.router

import ru.maksonic.beresta.navigation.router.AbstractRoute

/**
 * @Author maksonic on 15.11.2022
 */
object Destination : AbstractRoute("root") {
    object Splash : AbstractRoute("splash")
    object Onboarding : AbstractRoute("onboarding")
    object Main : AbstractRoute("main")
    object Settings : AbstractRoute("settings")
}