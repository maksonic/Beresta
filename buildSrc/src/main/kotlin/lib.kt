/**
 * @Author maksonic on 20.04.2023
 */
object lib {

    //Android
    private val Accompanist = "0.31.3-beta"
    private val CoreKtx = "1.10.1"
    private val Datastore = "1.0.0"
    private val Gson = "2.10.1"
    private val LifecycleRuntimeKtx = "2.6.1"
    private val SplashScreen = "1.0.0"

    //Compose
    private val ComposeActivity = "1.7.2"
    private val ComposeBom = "2023.05.01"
    private val ComposeMaterial3 = "1.2.0-alpha02"
    private val ComposeVersion = "1.5.0-beta01"

    //Images
    private val Coil = "2.2.2"
    private val Glide = "1.0.0-alpha.1"

    //JetBrains
    private val CoroutinesAndroid = "1.7.1"
    private val Json = "1.5.0"

    //Sl
    private val Koin = "3.4.5"

    //Db
    private val Room = "2.5.1"

    //Test
    private val Junit4 = "4.13.2"
    private val JunitExt = "1.1.5"
    private val Espresso = "3.5.1"

    object android {
        val coreKtx = "androidx.core:core-ktx:$CoreKtx"
        val datastore = "androidx.datastore:datastore-preferences:$Datastore"
        val gson = "com.google.code.gson:gson:$Gson"
        val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$LifecycleRuntimeKtx"
        val splashScreen = "androidx.core:core-splashscreen:$SplashScreen"
    }

    object accompanist {
        val navigation = "com.google.accompanist:accompanist-navigation-animation:$Accompanist"
        val pager = "com.google.accompanist:accompanist-pager:$Accompanist"
        val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:$Accompanist"
        val systemUiController =
            "com.google.accompanist:accompanist-systemuicontroller:$Accompanist"
    }

    object compose {
        val activity = "androidx.activity:activity-compose:$ComposeActivity"
        val animation = "androidx.compose.animation:animation:$ComposeVersion"
        val bom = "androidx.compose:compose-bom:$ComposeBom"
        val lifecycle = "androidx.lifecycle:lifecycle-runtime-compose"
        val material = "androidx.compose.material:material-android:$ComposeVersion"
        val material3 = "androidx.compose.material3:material3:$ComposeMaterial3"
        val runtime = "androidx.compose.runtime:runtime"
        val ui = "androidx.compose.ui:ui"
        val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    }

    object image {
        val coil = "io.coil-kt:coil-compose:$Coil"
        val glide = "com.github.bumptech.glide:compose:$Glide"
    }

    object jetBrains {
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$CoroutinesAndroid"
        val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:$Json"
    }

    object koin {
        val android = "io.insert-koin:koin-androidx-compose:$Koin"
    }

    object room {
        val compiler = "androidx.room:room-compiler:$Room"
        val runtime = "androidx.room:room-runtime:$Room"
        val ktx = "androidx.room:room-ktx:$Room"
    }

    object test {
        val composeUiManifest = "androidx.compose.ui:ui-test-manifest" // debugImplementation
        val composeUiTooling = "androidx.compose.ui:ui-tooling" // debugImplementation
        val composeJnit4Ui = "androidx.compose.ui:ui-test-junit4" // androidTestImplementation
        val espresso = "androidx.test.espresso:espresso-core:$Espresso" // androidTestImplementation
        val junit4 = "junit:junit:$Junit4" // testImplementation
        val junitExt = "androidx.test.ext:junit:$JunitExt" // androidTestImplementation
    }
}