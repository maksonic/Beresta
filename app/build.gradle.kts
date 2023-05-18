plugins {
    androidApp()
    kotlinAndroid()
}

android {
    namespace = module.primary.app.namespace
    compileSdk = androidConfig.compileSdk

    defaultConfig {
        applicationId = androidConfig.applicationId
        minSdk = androidConfig.minSdk
        targetSdk = androidConfig.targetSdk
        versionCode = androidConfig.versionCode
        versionName = androidConfig.versionName

        testInstrumentationRunner = androidConfig.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName(buildConfig.type.current) {
            isMinifyEnabled = androidConfig.isMinifyEnabled
            isShrinkResources = androidConfig.isShrinkResources

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = androidConfig.javaVersion
        targetCompatibility = androidConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = androidConfig.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = androidConfig.kotlinCompilerExtensionVersion
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
    implementation(project(module.screen.trashList.path))
    //libs
    implementation(lib.accompanist.navigation)
    implementation(platform(lib.compose.bom))
    implementation(lib.android.coreKtx)
    implementation(lib.android.lifecycleRuntimeKtx)
    implementation(lib.android.splashScreen)
    implementation(lib.compose.activity)
    implementation(lib.compose.ui)
    implementation(lib.compose.uiToolingPreview)
    implementation(lib.compose.material3)
    implementation(lib.compose.lifecycle)
    implementation(lib.koin.android)
    testImplementation(lib.test.junit4)
    androidTestImplementation(lib.test.junitExt)
    androidTestImplementation(lib.test.espresso)
    debugImplementation(lib.test.composeUiTooling)
    androidTestImplementation(lib.test.composeJnit4Ui)
    androidTestImplementation(platform(lib.compose.bom))
    debugImplementation(lib.test.composeUiManifest)
}