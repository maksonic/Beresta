plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = module.feature.onboarding.core.namespace
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk

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
    implementation(project(module.primary.elm.path))
    implementation(project(module.primary.data.common.path))
    implementation(project(module.primary.languageEngine.shell.path))
    implementation(project(module.primary.ui.theme.path))
    implementation(project(module.primary.ui.widget.path))
    implementation(project(module.feature.onboarding.api.path))
    implementation(project(module.feature.languagePicker.api.path))
    implementation(project(module.feature.themePicker.api.path))
    implementation(project(module.primary.navigation.router.path))
    implementation(project(module.common.jsonConverter.path))
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.datastore)
    implementation(libs.activity.compose)
    implementation(libs.lifecycle.compose)
    implementation(libs.material3)
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(libs.koin.android)
    implementation(libs.gson)
    implementation(libs.json)
    implementation(libs.koin.android)

    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}