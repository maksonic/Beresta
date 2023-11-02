package ru.maksonic.beresta.common.ui_theme

import java.util.Locale

/**
 * @Author maksonic on 14.10.2023
 */
enum class AppThemeUi(val title: String) {
    SYSTEM("System theme"),
    DAY("Day theme"),
    NIGHT( "Night theme"),
    DARK("Dark theme");

    override fun toString(): String = name.lowercase(Locale.ROOT)
}