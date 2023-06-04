plugins {
    androidLibrary()
    kotlinAndroid()
}

android {
    namespace = module.feature.languagePicker.core.namespace
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
    implementation(project(module.primary.languageEngine.shell.path))
    implementation(project(module.feature.languagePicker.api.path))
    implementation(project(module.primary.ui.theme.path))
    implementation(project(module.primary.ui.widget.path))
    implementation(platform(lib.compose.bom))
    implementation(lib.compose.activity)
    implementation(lib.compose.lifecycle)
    implementation(lib.compose.material3)
    implementation(lib.compose.ui)
    implementation(lib.compose.uiToolingPreview)
    implementation(lib.koin.android)
    debugImplementation(lib.test.composeUiManifest)
    debugImplementation(lib.test.composeUiTooling)
}