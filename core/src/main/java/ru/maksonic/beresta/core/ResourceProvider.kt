package ru.maksonic.beresta.core

import android.content.Context
import androidx.annotation.StringRes

/**
 * @Author maksonic on 24.01.2023
 */
interface ResourceProvider {
    fun getString(@StringRes id: Int, vararg formatArgs: Any?): String

    class Core(private val context: Context) : ResourceProvider {

        override fun getString(id: Int, vararg formatArgs: Any?) =
            context.getString(id, *formatArgs)
    }
}