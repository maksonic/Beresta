package ru.maksonic.beresta.platform.core.ui

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.common.data.Datastore
import ru.maksonic.beresta.common.ui_theme.AppAnimationVelocity

/**
 * @Author maksonic on 14.10.2023
 */
interface AnimationVelocity {
    val current: Flow<AppAnimationVelocity>
    suspend fun update(key: AppAnimationVelocity.Key)

    class Core(private val datastore: Datastore) : AnimationVelocity {

        private val defaultAnimVelocity = AppAnimationVelocity.Key.NORMAL
        private val key = stringPreferencesKey("prefs_app_animation_velocity_key")

        override val current: Flow<AppAnimationVelocity> =
            datastore.datastore.data.map { prefs ->
                val animations = when (AppAnimationVelocity.Key.valueOf(
                    prefs[key] ?: defaultAnimVelocity.name
                )) {
                    AppAnimationVelocity.Key.DISABLE -> AppAnimationVelocity.Disabled
                    AppAnimationVelocity.Key.SLOW -> AppAnimationVelocity.Slow
                    AppAnimationVelocity.Key.NORMAL -> AppAnimationVelocity.Normal
                    AppAnimationVelocity.Key.FAST -> AppAnimationVelocity.Fast
                    AppAnimationVelocity.Key.VERY_FAST -> AppAnimationVelocity.VeryFast
                }
                animations
            }

        override suspend fun update(key: AppAnimationVelocity.Key) {
            datastore.datastore.edit { prefs -> prefs[this.key] = key.name }
        }
    }
}