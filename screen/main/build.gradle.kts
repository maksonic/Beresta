plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = module.Screen.Main.namespace
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = AndroidConfig.KOTLIN_COMPILER_EXT
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(module.Common.Core.path))
    implementation(project(module.Common.Domain.path))
    implementation(project(module.Common.UiKit.path))
    implementation(project(module.Common.UiTheme.path))
    implementation(project(module.LanguageEngine.Shell.path))
    implementation(project(module.Platform.Elm.path))
    implementation(project(module.Platform.Navigation.Router.path))
    implementation(project(module.Feature.AddFolderDialog.Ui.Api.path))
    implementation(project(module.Feature.EditNote.Ui.Api.path))
    implementation(project(module.Feature.FoldersList.Domain.path))
    implementation(project(module.Feature.FoldersList.Ui.Api.path))
    implementation(project(module.Feature.HiddenNotesDialog.Domain.path))
    implementation(project(module.Feature.HiddenNotesDialog.Ui.Api.path))
    implementation(project(module.Feature.MarkerColorPicker.Domain.path))
    implementation(project(module.Feature.NotesList.Domain.path))
    implementation(project(module.Feature.NotesList.Ui.Api.path))
    implementation(project(module.Feature.SearchBar.Ui.Api.path))
    implementation(project(module.Feature.SortingSheet.Domain.path))
    implementation(project(module.Feature.SortingSheet.Ui.Api.path))
    implementation(project(module.Feature.WallpaperPicker.Domain.path))
    implementation(project(module.Feature.WallpaperPicker.Ui.Api.path))
    implementation(libs.activity.compose)
    implementation(libs.compose.constraint.layout)
    implementation(libs.compose.lifecycle)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
    implementation(libs.koin.android)
}