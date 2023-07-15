package ru.maksonic.beresta.screen.settings.appearance.core

import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.ui.theme.component.AppAnimationVelocity

/**
 * @Author maksonic on 14.07.2023
 */
interface AnimationVelocity {
    val current: SharedUiState<AppAnimationVelocity.Key>
    val currentCacheVelocity: Flow<AppAnimationVelocity>
    suspend fun updateInDatastore(key: AppAnimationVelocity.Key)

    class Core(
        private val datastore: Datastore,
        override val current: SharedUiState<AppAnimationVelocity.Key>
    ) : AnimationVelocity {
        private val defaultAnimVelocity = AppAnimationVelocity.Key.NORMAL
        private val key = stringPreferencesKey("prefs_app_animation_velocity_key")

        override val currentCacheVelocity: Flow<AppAnimationVelocity> =
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

        override suspend fun updateInDatastore(key: AppAnimationVelocity.Key) {
            Log.e("AAA", "UPDATE")
            datastore.datastore.edit { prefs -> prefs[this.key] = key.name }
        }
    }
}