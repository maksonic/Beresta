/**
 * @Author maksonic on 15.12.2022
 */
object Lib {

    private object Version {
        //accompanist
        const val accompanist = "0.28.0"

        //android
        const val coreKtx = "1.9.0"
        const val datastore = "1.0.0"
        const val lifecycle = "2.5.1"
        const val navigation = "2.6.0-alpha04"
        const val splashScreen = "1.0.0"
        //compose

        const val activityCompose = "1.6.1"
        const val composeBom = "2023.01.00"
        const val composeLifecycle = "2.6.0-beta01"
        const val material3 = "1.1.0-alpha07"

        //coroutines
        const val coroutinesAndroid = "1.6.4"

        //Gson
        const val gson = "2.10.1"

        //Json
        const val json = "1.5.0-RC"

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
        const val pagerIndicators =
            "com.google.accompanist:accompanist-pager-indicators:${Version.accompanist}"
        const val systemUiController =
            "com.google.accompanist:accompanist-systemuicontroller:${Version.accompanist}"
    }

    object Android {
        const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
        const val datastore = "androidx.datastore:datastore-preferences:${Version.datastore}"
        const val gson = "com.google.code.gson:gson:${Version.gson}"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
    }

    object Compose {
        const val activity = "androidx.activity:activity-compose:${Version.activityCompose}"
        const val bom = "androidx.compose:compose-bom:${Version.composeBom}"
        const val foundation = "androidx.compose.foundation:foundation"
        const val lifecycle =
            "androidx.lifecycle:lifecycle-runtime-compose:${Version.composeLifecycle}"
        const val material = "androidx.compose.material:material"
        const val material3 = "androidx.compose.material3:material3:${Version.material3}"
        const val navigation = "androidx.navigation:navigation-compose"
        const val ui = "androidx.compose.ui:ui"
        const val uiPreview = "androidx.compose.ui:ui-tooling-preview"
    }

    object JetBrains {
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesAndroid}"
        const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.json}"
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
        const val composeManifest = "androidx.compose.ui:ui-test-manifest"
        const val composeTooling = "androidx.compose.ui:ui-tooling:"
    }
}