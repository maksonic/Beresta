plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.plugin.serialization)
}

android {
    namespace = module.Feature.WallpaperPicker.Data.namespace
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(module.Common.Core.path))
    implementation(project(module.Common.Data.path))
    implementation(project(module.Common.Domain.path))
    implementation(project(module.Common.UiTheme.path))
    implementation(project(module.Feature.WallpaperPicker.Domain.path))
    implementation(libs.datastore)
    implementation(libs.json)
    implementation(libs.koin.android)

}