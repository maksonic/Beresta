plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = module.primary.languageEngine.core.namespace
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(module.primary.languageEngine.shell.path))
    implementation(project(module.primary.data.common.path))
    implementation(project(module.common.coroutineDispatchers.path))
    implementation(project(module.common.jsonConverter.path))
    implementation(libs.gson)
    implementation(libs.datastore)
    implementation(libs.json)
    implementation(libs.koin.android)
}