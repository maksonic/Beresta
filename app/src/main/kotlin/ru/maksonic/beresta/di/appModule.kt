package ru.maksonic.beresta.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.core.MainActivityProgram
import ru.maksonic.beresta.core.MainActivitySandbox
import ru.maksonic.beresta.core.VibrationPerformer
import ru.maksonic.beresta.core.VibrationPerformerCore

/**
 * @Author maksonic on 18.06.2023
 */
internal val appModule = module {
    single<VibrationPerformer> { VibrationPerformerCore(datastore = get()) }

    single {
        MainActivityProgram(
            languageEngineApi = get(),
            languageProvider = get(),
            themeFeatureApi = get(),
            paletteFeatureApi = get(),
            animationVelocity = get()
        )
    }
    viewModel { MainActivitySandbox(mainActivityProgram = get()) }
}