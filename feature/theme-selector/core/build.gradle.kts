plugins {
    androidLibrary()
    kotlinAndroid()
}

android {
    namespace = BaseModule.Feature.ThemeSelector.Core.namespace
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    buildTypes {
        getByName(Build.Type.CURRENT) {
            isMinifyEnabled = Config.isMinifyEnabled

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }

    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.kcExtVersion
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(BaseModule.Data.Common.path))
    implementation(project(BaseModule.Feature.ThemeSelector.Api.path))
    implementation(project(BaseModule.Feature.LanguageSelector.Api.path))
    implementation(project(BaseModule.Ui.Theme.path))
    implementation(project(BaseModule.Ui.Widget.path))
    implementation(Lib.Android.datastore)
    implementation(platform((Lib.Compose.bom)))
    implementation(Lib.Compose.material)
    implementation(Lib.Compose.ui)
    implementation(Lib.Compose.uiPreview)
    implementation(Lib.Koin.compose)

    debugImplementation(Lib.Test.composeManifest)
    debugImplementation(Lib.Test.composeTooling)
}