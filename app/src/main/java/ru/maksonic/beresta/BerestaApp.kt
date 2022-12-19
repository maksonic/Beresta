package ru.maksonic.beresta

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.maksonic.beresta.core.coreModule
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.data.database.databaseModule
import ru.maksonic.beresta.feature.onboarding.data.onboardingDataModule
import ru.maksonic.beresta.feature.onboarding.ui.di.onboardingFeatureModule
import ru.maksonic.beresta.feature.theme_selector.themeSelectorFeatureModule
import ru.maksonic.beresta.navigation.graph_builder.navigationModule
import ru.maksonic.beresta.screen.main.core.mainScreenModule

/**
 * @Author maksonic on 15.12.2022
 */
class BerestaApp : Application() {

    private val appModule = module {
        single<Datastore> { Datastore.Core(androidContext()) }
        viewModelOf(::MainViewModel)
    }
    private val modules = listOf(
        appModule,
        navigationModule,
        databaseModule,
        onboardingDataModule,
        onboardingFeatureModule,
        mainScreenModule,
        themeSelectorFeatureModule,
        coreModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BerestaApp)
            modules(modules)
        }
    }
}