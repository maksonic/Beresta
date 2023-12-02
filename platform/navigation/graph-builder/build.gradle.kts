plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = module.Platform.Navigation.GraphBuilder.namespace
    compileSdk = AndroidConfig.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfig.MIN_SDK
        testInstrumentationRunner = AndroidConfig.TEST_RUNNER
    }

    buildTypes {
        getByName(BuildConfig.Type.CURRENT) {
            isMinifyEnabled = AndroidConfig.IS_MINIFY

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = AndroidConfig.JAVA_VERSION
        targetCompatibility = AndroidConfig.JAVA_VERSION
    }

    kotlinOptions {
        jvmTarget = AndroidConfig.JVM_TARGET
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = AndroidConfig.KOTLIN_COMPILER_EXT
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(module.Common.UiTheme.path))
    implementation(project(module.Platform.Navigation.Router.path))
    implementation(project(module.Screen.Folders.path))
    implementation(project(module.Screen.HiddenNotes.path))
    implementation(project(module.Screen.Main.path))
    implementation(project(module.Screen.Settings.Appearance.path))
    implementation(project(module.Screen.Settings.Main.path))
    implementation(project(module.Screen.Settings.Notifications.path))
    implementation(project(module.Screen.Settings.Security.path))
    implementation(project(module.Screen.Settings.Tags.path))
    implementation(project(module.Screen.Splash.path))
    implementation(project(module.Screen.Trash.Folders.path))
    implementation(project(module.Screen.Trash.Notes.path))
    implementation(project(module.Feature.Onboarding.Ui.Api.path))
    implementation(project(module.Feature.EditNote.Ui.Api.path))
    implementation(project(module.Feature.ImageViewer.Ui.Api.path))
    implementation(project(module.Feature.TagsList.Ui.Api.path))
    implementation(libs.compose.navigation)
    implementation(libs.koin.android)
}