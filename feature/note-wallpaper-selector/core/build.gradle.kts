plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinSerialization()
}

android {
    namespace = BaseModule.Feature.NoteWallpaperSelector.Core.namespace
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

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(BaseModule.Feature.LanguageSelector.Api.path))
    implementation(project(BaseModule.Feature.NoteWallpaperSelector.Api.path))
    implementation(project(BaseModule.Core.path))
    implementation(project(BaseModule.Elm.path))
    implementation(project(BaseModule.Ui.Theme.path))
    implementation(project(BaseModule.Ui.Widget.path))
    implementation(platform((Lib.Compose.bom)))
    implementation(Lib.Accompanist.pager)
    implementation(Lib.Accompanist.pagerIndicators)
    implementation(Lib.Image.glide)
    implementation(Lib.Compose.lifecycle)
    implementation(Lib.Compose.material)
    implementation(Lib.Compose.material3)
    implementation(Lib.Compose.ui)
    implementation(Lib.Koin.compose)
}