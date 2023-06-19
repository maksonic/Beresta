plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = module.primary.app.namespace
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        applicationId = AndroidConfig.applicationId
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName(BuildConfig.Type.current) {
            isMinifyEnabled = AndroidConfig.isMinifyEnabled
            isShrinkResources = AndroidConfig.isShrinkResources

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = AndroidConfig.javaVersion
        targetCompatibility = AndroidConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = AndroidConfig.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AndroidConfig.kotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    android.sourceSets.all {
        kotlin.srcDir("src/$name/kotlin")
    }
}

dependencies {
    //primary
    implementation(project(module.primary.elm.path))
    implementation(project(module.primary.languageEngine.core.path))
    implementation(project(module.primary.languageEngine.shell.path))
    implementation(project(module.primary.navigation.graphBuilder.path))
    implementation(project(module.primary.navigation.router.path))
    implementation(project(module.primary.ui.theme.path))
    implementation(project(module.primary.ui.widget.path))
    implementation(project(module.common.coroutineDispatchers.path))
    implementation(project(module.common.jsonConverter.path))
    implementation(project(module.primary.data.common.path))
    implementation(project(module.primary.data.database.path))
    //features
    implementation(project(module.feature.splashScreen.api.path))
    implementation(project(module.feature.splashScreen.core.path))

    implementation(project(module.feature.onboarding.api.path))
    implementation(project(module.feature.onboarding.core.path))

    implementation(project(module.feature.languagePicker.api.path))
    implementation(project(module.feature.languagePicker.core.path))

    implementation(project(module.feature.themePicker.api.path))
    implementation(project(module.feature.themePicker.core.path))

    implementation(project(module.feature.editNote.api.path))
    implementation(project(module.feature.editNote.core.path))

    implementation(project(module.feature.notes.list.api.path))
    implementation(project(module.feature.notes.list.core.path))
    implementation(project(module.feature.notes.folders.api.path))
    implementation(project(module.feature.notes.folders.core.path))
    implementation(project(module.primary.data.notes.path))
    implementation(project(module.primary.data.notesFolders.path))

    implementation(project(module.feature.searchBar.api.path))
    implementation(project(module.feature.searchBar.core.path))

    implementation(project(module.feature.topBarCounter.api.path))
    implementation(project(module.feature.topBarCounter.core.path))

    //screens
    implementation(project(module.screen.main.path))
    implementation(project(module.screen.settings.path))
    implementation(project(module.screen.trashList.notes.path))
    implementation(project(module.screen.trashList.folders.path))
    //libs
    implementation(libs.accompanist.navigation)
    implementation(platform(libs.compose.bom))
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.splash.screen)
    implementation(libs.activity.compose)
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.lifecycle.compose)
    implementation(libs.koin.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}