plugins {
    androidLibrary()
    kotlinAndroid()
}

android {
    namespace = BaseModule.Feature.EditNote.Ui.namespace
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
    implementation(project(BaseModule.Ui.Theme.path))
    implementation(project(BaseModule.Ui.Widget.path))
    implementation(project(BaseModule.Elm.path))
    implementation(project(BaseModule.BaseDomain.path))
    implementation(project(BaseModule.Feature.EditNote.Domain.path))
    implementation(project(BaseModule.Feature.NotesList.Api.path))
    implementation(project(BaseModule.Feature.NotesList.Domain.path))
    implementation(project(BaseModule.Navigation.Router.path))
    implementation(project(BaseModule.Core.path))
    implementation(Lib.Compose.material)
    implementation(Lib.Compose.material3)
    implementation(Lib.Compose.ui)
    implementation(Lib.Compose.uiPreview)
    implementation(Lib.Koin.compose)

    androidTestImplementation(Lib.Test.composeJunit)
    debugImplementation(Lib.Test.composeManifest)
    debugImplementation(Lib.Test.composeTooling)
}