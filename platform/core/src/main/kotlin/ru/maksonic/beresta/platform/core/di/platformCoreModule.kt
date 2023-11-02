package ru.maksonic.beresta.platform.core.di

import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.maksonic.beresta.common.core.ScreenCaptureManager
import ru.maksonic.beresta.common.core.VibrationManager
import ru.maksonic.beresta.common.core.VibrationPerformer
import ru.maksonic.beresta.common.core.json.AssetsReader
import ru.maksonic.beresta.common.core.json.JsonConverter
import ru.maksonic.beresta.common.core.security.BiometricEngine
import ru.maksonic.beresta.common.core.security.CryptoEngine
import ru.maksonic.beresta.common.data.Datastore
import ru.maksonic.beresta.platform.core.DatastoreCore
import ru.maksonic.beresta.platform.core.json.AssetsReaderCore
import ru.maksonic.beresta.platform.core.json.JsonConverterCore
import ru.maksonic.beresta.platform.core.security.BiometricEngineCore
import ru.maksonic.beresta.platform.core.security.CryptoEngineCore
import ru.maksonic.beresta.platform.core.system.vibration.VibrationManagerCore
import ru.maksonic.beresta.platform.core.system.vibration.VibrationPerformerCore
import ru.maksonic.beresta.platform.core.ui.AnimationVelocity
import ru.maksonic.beresta.platform.core.ui.ScreenCaptureManagerCore

/**
 * @Author maksonic on 14.10.2023
 */
val platformCoreModule = module {
    single<Datastore> { DatastoreCore(androidContext()) }
    single { Json { ignoreUnknownKeys = true } }
    single<AssetsReader> { AssetsReaderCore(androidContext()) }
    single<JsonConverter> { JsonConverterCore(assetsReader = get()) }
    single<VibrationPerformer> { VibrationPerformerCore(datastore = get()) }
    single<VibrationManager> {
        VibrationManagerCore(
            vibrationPerformer = get(),
            context = androidContext()
        )
    }
    single<AnimationVelocity> { AnimationVelocity.Core(datastore = get()) }
    single<ScreenCaptureManager> { ScreenCaptureManagerCore() }
    factory<CryptoEngine> { CryptoEngineCore() }
    factory<BiometricEngine> { BiometricEngineCore(context = androidContext()) }

}