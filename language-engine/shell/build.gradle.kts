plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinSerialization()
}

android {
    namespace = module.primary.languageEngine.shell.namespace
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
    implementation(platform(lib.compose.bom))
    implementation(lib.compose.runtime)
    implementation(lib.android.gson)
    implementation(lib.jetBrains.json)
}