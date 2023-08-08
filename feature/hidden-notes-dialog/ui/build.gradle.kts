plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = module.feature.hiddenNotesDialog.ui.namespace
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
    implementation(project(module.primary.core.path))
    implementation(project(module.primary.elm.path))
    implementation(project(module.primary.languageEngine.shell.path))
    implementation(project(module.primary.navigation.router.path))
    implementation(project(module.primary.ui.theme.path))
    implementation(project(module.primary.ui.widget.path))
    implementation(project(module.common.coroutineDispatchers.path))
    implementation(project(module.feature.notes.api.path))
    implementation(project(module.feature.hiddenNotesDialog.api.path))
    implementation(platform(libs.compose.bom))
    implementation(libs.activity.compose)
    implementation(libs.coroutinesAndroid)
    implementation(libs.datastore)
    implementation(libs.material3)
    implementation(libs.lifecycle.compose)
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(libs.koin.android)
    implementation(libs.workmanager)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}