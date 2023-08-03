plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = module.primary.navigation.graphBuilder.namespace
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
    }

    buildTypes {
        getByName(BuildConfig.Type.current) {
            isMinifyEnabled = AndroidConfig.isMinifyEnabled

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
}

dependencies {
    implementation(project(module.primary.navigation.router.path))
    implementation(project(module.primary.ui.theme.path))
    implementation(project(module.screen.main.path))
    implementation(project(module.screen.settings.path))
    implementation(project(module.screen.settingsAppearance.path))
    implementation(project(module.screen.settingsSecurity.path))
    implementation(project(module.screen.folders.path))
    implementation(project(module.screen.trashList.notes.path))
    implementation(project(module.screen.trashList.folders.path))
    implementation(project(module.screen.hiddenNotes.path))
    implementation(project(module.feature.onboarding.api.path))
    implementation(project(module.feature.splashScreen.api.path))
    implementation(project(module.feature.editNote.api.path))
    implementation(libs.accompanist.navigation)
    implementation(libs.compose.animation)
    implementation(libs.koin.android)
}