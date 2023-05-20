package ru.maksonic.beresta.navigation.router.navigator

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import ru.maksonic.beresta.navigation.router.Destination

/**
 * @Author maksonic on 24.01.2023
 */
abstract class AbstractNavigator : ArgumentReceiver {
    lateinit var navController: NavHostController

    fun navigate(
        from: NavBackStackEntry,
        destination: String,
        isPopUp: Boolean = true,
        popUpRoute: String = Destination.Onboarding.route
    ) {
        if (from.lifecycleIsResumed())
            navController.navigate(destination) {
                isPopUpTo(isPopUp, popUpRoute)
            }
    }

    fun backPressed() = navController.navigateUp()

    private fun NavOptionsBuilder.isPopUpTo(isPopUp: Boolean, route: String) {
        if (isPopUp) {
            popUpTo(route) {
                inclusive = true
            }
        } else {
            popUpTo(0)
        }
    }

    private fun NavBackStackEntry.lifecycleIsResumed() =
        this.getLifecycle().currentState == Lifecycle.State.RESUMED

    override fun getBoolean(key: String): Boolean =
        navController.currentBackStackEntry?.arguments?.getBoolean(key) ?: false

    override fun getLong(key: String): Long =
        navController.currentBackStackEntry?.arguments?.getLong(key) ?: 0

    override fun getString(key: String): String =
        navController.currentBackStackEntry?.arguments?.getString(key) ?: ""
}
