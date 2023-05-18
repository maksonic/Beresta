plugins {
    androidLibrary()
    kotlinAndroid()
}

android {
    namespace = module.primary.navigation.graphBuilder.namespace
    compileSdk = androidConfig.compileSdk

    defaultConfig {
        minSdk = androidConfig.minSdk
        targetSdk = androidConfig.targetSdk

        testInstrumentationRunner = androidConfig.testInstrumentationRunner
    }

    buildTypes {
        getByName(buildConfig.type.current) {
            isMinifyEnabled = androidConfig.isMinifyEnabled

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
}

dependencies {
    implementation(project(module.primary.navigation.router.path))
    implementation(project(module.screen.main.path))
    implementation(project(module.screen.settings.path))
    implementation(project(module.screen.trashList.path))
    implementation(project(module.feature.onboarding.api.path))
    implementation(project(module.feature.splashScreen.api.path))
    implementation(project(module.feature.editNote.api.path))
    implementation(project(module.feature.notes.folders.api.path))
    implementation(lib.accompanist.navigation)
    implementation(lib.koin.android)

}