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

        testInstrumentationRunner = Config.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
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
    implementation(project(BaseModule.Core.path))
    implementation(project(BaseModule.Elm.path))
    implementation(project(BaseModule.Navigation.Router.path))
    implementation(project(BaseModule.Navigation.GraphBuilder.path))
    implementation(project(BaseModule.Data.Common.path))
    implementation(project(BaseModule.Data.Database.path))

    implementation(project(BaseModule.Feature.Onboarding.Data.path))
    implementation(project(BaseModule.Feature.Onboarding.Domain.path))
    implementation(project(BaseModule.Feature.Onboarding.Ui.path))

    implementation(project(BaseModule.Feature.BottomPanel.Api.path))
    implementation(project(BaseModule.Feature.BottomPanel.Ui.path))


    implementation(project(BaseModule.Feature.NotesList.Api.path))
    implementation(project(BaseModule.Feature.NotesList.Data.path))
    implementation(project(BaseModule.Feature.NotesList.Domain.path))
    implementation(project(BaseModule.Feature.NotesList.Ui.path))

    implementation(project(BaseModule.Feature.TasksList.Api.path))
    implementation(project(BaseModule.Feature.TasksList.Data.path))
    implementation(project(BaseModule.Feature.TasksList.Domain.path))
    implementation(project(BaseModule.Feature.TasksList.Ui.path))

    implementation(project(BaseModule.Feature.ThemeSelector.path))
    implementation(project(BaseModule.Feature.SplashScreen.path))
    implementation(project(BaseModule.Ui.Theme.path))
    implementation(project(BaseModule.Ui.Widget.path))

    implementation(project(BaseModule.Screen.Main.path))
    
    implementation(Lib.Accompanist.navigation)
    implementation(Lib.Accompanist.systemUiController)
    implementation(Lib.Android.coreKtx)
    implementation(Lib.Android.lifecycle)
    implementation(Lib.Compose.activity)
    implementation(Lib.Compose.lifecycle)
    implementation(Lib.Compose.material)
    implementation(Lib.Compose.ui)
    implementation(Lib.Compose.uiPreview)
    implementation(Lib.Koin.compose)

    testImplementation(Lib.Test.junit)
    androidTestImplementation(Lib.Test.junitExt)
    androidTestImplementation(Lib.Test.composeJunit)
    debugImplementation(Lib.Test.composeManifest)
    debugImplementation(Lib.Test.composeTooling)
}