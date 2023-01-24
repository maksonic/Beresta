package ru.maksonic.beresta.navigation.router

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

/**
 * @Author maksonic on 24.01.2023
 */
abstract class AbstractNavigator {
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
        this.lifecycle.currentState == Lifecycle.State.RESUMED

    fun getStringArgument(key: String): String =
        navController.currentBackStackEntry?.arguments?.getString(key) ?: ""

    fun getLongArgument(key: String): Long =
        navController.currentBackStackEntry?.arguments?.getLong(key) ?: 0
}