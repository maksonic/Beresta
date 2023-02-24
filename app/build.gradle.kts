plugins {
    androidApp()
    kotlinAndroid()
}

android {
    namespace = BaseModule.App.namespace
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.appId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName(Build.Type.CURRENT) {
            isMinifyEnabled = Config.isMinifyEnabled

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    implementation(project(BaseModule.Core.path))

    implementation(project(BaseModule.Data.Common.path))
    implementation(project(BaseModule.Data.Database.path))
    implementation(project(BaseModule.Data.Notes.path))

    implementation(project(BaseModule.Elm.path))

    implementation(project(BaseModule.Navigation.Router.path))
    implementation(project(BaseModule.Navigation.GraphBuilder.path))
    implementation(project(BaseModule.Screen.Settings.path))

    implementation(project(BaseModule.Screen.Main.path))

    implementation(project(BaseModule.Feature.SplashScreen.Api.path))
    implementation(project(BaseModule.Feature.SplashScreen.Core.path))

    implementation(project(BaseModule.Feature.Onboarding.Api.path))
    implementation(project(BaseModule.Feature.Onboarding.Core.path))

    implementation(project(BaseModule.Feature.ThemeSelector.Api.path))
    implementation(project(BaseModule.Feature.ThemeSelector.Core.path))

    implementation(project(BaseModule.Feature.LanguageSelector.Api.path))
    implementation(project(BaseModule.Feature.LanguageSelector.Core.path))

    implementation(project(BaseModule.Feature.NotesList.Api.path))
    implementation(project(BaseModule.Feature.NotesList.Core.path))

    implementation(project(BaseModule.Feature.SearchBar.Api.path))
    implementation(project(BaseModule.Feature.SearchBar.Core.path))

    implementation(project(BaseModule.Feature.EditNote.Api.path))
    implementation(project(BaseModule.Feature.EditNote.Core.path))

    implementation(project(BaseModule.Ui.Theme.path))
    implementation(project(BaseModule.Ui.Widget.path))

    implementation(Lib.Accompanist.navigation)
    implementation(Lib.Accompanist.systemUiController)
    implementation(Lib.Android.coreKtx)
    implementation(Lib.Android.gson)
    implementation(Lib.Android.lifecycle)
    implementation(platform(Lib.Compose.bom))
    implementation(Lib.Compose.activity)
    implementation(Lib.Compose.ui)
    implementation(Lib.Compose.uiPreview)
    implementation(Lib.Compose.lifecycle)
    implementation(Lib.Compose.material)
    implementation(Lib.Compose.material3)
    implementation(Lib.JetBrains.json)
    implementation(Lib.Koin.compose)

    debugImplementation(Lib.Test.composeTooling)
    debugImplementation(Lib.Test.composeManifest)
}