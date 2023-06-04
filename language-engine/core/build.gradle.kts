plugins {
    androidLibrary()
    kotlinAndroid()
}

android {
    namespace = module.primary.languageEngine.core.namespace
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
    implementation(lib.android.gson)
    implementation(lib.android.datastore)
    implementation(lib.jetBrains.json)
    implementation(lib.koin.android)
}