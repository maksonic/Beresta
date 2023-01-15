package ru.maksonic.beresta.navigation.router

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

/**
 * @Author maksonic on 15.11.2022
 */
abstract class AbstractNavigator {
    lateinit var navController: NavHostController

    fun backPressed() = navController.popBackStack()

    private fun NavOptionsBuilder.isPopUpTo(isPopUp: Boolean, route: String ) {
        if (isPopUp) {
            popUpTo(route) {
                inclusive = true
            }
        } else {
            popUpTo(0)
        }
    }

    fun navigate(
        destination: String,
        isPopUp: Boolean = true,
        popUpRoute: String = Destination.Onboarding.route
    ) {
        if (destination != navController.currentDestination?.route)
            navController.navigate(destination) {
               isPopUpTo(isPopUp, popUpRoute)
            }
        else return
    }

    fun getStringArgument(key: String): String =
        navController.currentBackStackEntry?.arguments?.getString(key) ?: ""

    fun getLongArgument(key: String): Long =
        navController.currentBackStackEntry?.arguments?.getLong(key) ?: 0
}