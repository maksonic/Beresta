/**
 * @Author maksonic on 15.12.2022
 */
object Lib {
    private object Version {
        //accompanist
        const val accompanist = "0.27.1"
        //android
        const val coreKtx = "1.9.0"
        const val datastore = "1.0.0"
        const val lifecycle = "2.5.1"
        const val navigation = "2.5.3"
        const val splashScreen = "1.0.0"

        //compose
        const val compose = Config.composeVersion
        const val activityCompose = "1.6.1"
        const val composeLifecycle = "2.6.0-alpha03"

        //coroutines
        const val coroutinesAndroid = "1.6.4"

        //database
        const val room = "2.4.3"

        //sl
        const val koin = "3.3.0"

        //test
        const val junit = "4.13.2"
        const val junitExt = "1.1.3"
    }

    object Accompanist {
        const val navigation =
            "com.google.accompanist:accompanist-navigation-animation:${Version.accompanist}"
        const val pager = "com.google.accompanist:accompanist-pager:${Version.accompanist}"
        const val systemUiController =
            "com.google.accompanist:accompanist-systemuicontroller:${Version.accompanist}"
    }

    object Android {
        const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
        const val datastore = "androidx.datastore:datastore-preferences:${Version.datastore}"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
        const val navigationRuntime =
            "androidx.navigation:navigation-runtime-ktx:${Version.navigation}"

    }

    object Compose {
        const val activity = "androidx.activity:activity-compose:${Version.activityCompose}"
        const val lifecycle =
            "androidx.lifecycle:lifecycle-runtime-compose:${Version.composeLifecycle}"
        const val material = "androidx.compose.material:material:${Version.compose}"
        const val ui = "androidx.compose.ui:ui:${Version.compose}"
        const val uiPreview = "androidx.compose.ui:ui-tooling-preview:${Version.compose}"
    }

    object JetBrains {
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesAndroid}"
    }

    object Koin {
        const val compose = "io.insert-koin:koin-androidx-compose:${Version.koin}"
    }

    object Room {
        const val compiler = "androidx.room:room-compiler:${Version.room}"
        const val runtime = "androidx.room:room-runtime:${Version.room}"
        const val ktx = "androidx.room:room-ktx:${Version.room}"
    }

    object Test {
        const val junit = "junit:junit:${Version.junit}"
        const val junitExt = "androidx.test.ext:junit:${Version.junitExt}"
        const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Version.compose}"
        const val composeManifest = "androidx.compose.ui:ui-test-manifest:${Version.compose}"
        const val composeTooling = "androidx.compose.ui:ui-tooling:${Version.compose}"
    }
}