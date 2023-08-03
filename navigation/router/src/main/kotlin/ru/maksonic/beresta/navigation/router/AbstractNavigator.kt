package ru.maksonic.beresta.navigation.router

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

/**
 * @Author maksonic on 24.01.2023
 */
abstract class AbstractNavigator : ArgumentReceiver, Router {
    lateinit var navController: NavHostController

    fun init(controller: NavHostController) {
        navController = controller
    }

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

    override fun getBoolean(key: String): Boolean =
        navController.currentBackStackEntry?.arguments?.getBoolean(key) ?: false

    override fun getLong(key: String): Long =
        navController.currentBackStackEntry?.arguments?.getLong(key) ?: 0

    override fun getLongArray(key: String): LongArray? =
        navController.currentBackStackEntry?.arguments?.getLongArray(key)

    override fun getString(key: String): String =
        navController.currentBackStackEntry?.arguments?.getString(key) ?: ""

    override fun getListLongFromString(key: String): List<Long> {
        val data = navController.currentBackStackEntry?.arguments?.getString(key) ?: ""

        return if (data.length > 2) {
            data.replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .split(",")
                .toTypedArray()
                .map { it.toLong() }
        } else emptyList()
    }

    override fun getNoteEditorArgs(keys: List<String>): Pair<Boolean, Long> {
        val args = navController.currentBackStackEntry?.arguments
        val isHiddenNote = args?.getBoolean(keys.first()) ?: false
        val passedId = args?.getLong(keys.last()) ?: 0L
        return Pair(isHiddenNote, passedId)
    }
}
