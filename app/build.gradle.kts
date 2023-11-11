plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.plugin.serialization)
}

android {
    namespace = module.App.namespace
    compileSdk = AndroidConfig.COMPILE_SDK

    defaultConfig {
        applicationId = AndroidConfig.APP_ID
        minSdk = AndroidConfig.MIN_SDK
        targetSdk = AndroidConfig.TARGET_SDK
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME

        testInstrumentationRunner = AndroidConfig.TEST_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = AndroidConfig.IS_MINIFY
            isShrinkResources = AndroidConfig.IS_SHRINK_RESOURCES

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
    // Common
    implementation(project(module.Common.Data.path))
    implementation(project(module.Common.Domain.path))
    implementation(project(module.Common.Core.path))
    implementation(project(module.Common.UiKit.path))
    implementation(project(module.Common.UiTheme.path))
    implementation(project(module.Database.path))
    implementation(project(module.LanguageEngine.Core.path))
    implementation(project(module.LanguageEngine.Shell.path))
    // Platform
    implementation(project(module.Platform.Core.path))
    implementation(project(module.Platform.Elm.path))
    implementation(project(module.Platform.Navigation.GraphBuilder.path))
    implementation(project(module.Platform.Navigation.Router.path))
    // Screen
    implementation(project(module.Screen.Folders.path))
    implementation(project(module.Screen.HiddenNotes.path))
    implementation(project(module.Screen.Main.path))
    implementation(project(module.Screen.Splash.path))
    implementation(project(module.Screen.Settings.Appearance.path))
    implementation(project(module.Screen.Settings.Main.path))
    implementation(project(module.Screen.Settings.Notifications.path))
    implementation(project(module.Screen.Settings.Security.path))
    implementation(project(module.Screen.Trash.Folders.path))
    implementation(project(module.Screen.Trash.Notes.path))
    // Feature
    implementation(project(module.Feature.AddFolderDialog.Ui.Api.path))
    implementation(project(module.Feature.AddFolderDialog.Ui.Core.path))
    implementation(project(module.Feature.AppLang.Data.path))
    implementation(project(module.Feature.AppLang.Domain.path))
    implementation(project(module.Feature.AppTheme.Data.path))
    implementation(project(module.Feature.AppTheme.Domain.path))
    implementation(project(module.Feature.EditNote.Ui.Api.path))
    implementation(project(module.Feature.EditNote.Ui.Core.path))
    implementation(project(module.Feature.LanguagePicker.Ui.Api.path))
    implementation(project(module.Feature.LanguagePicker.Ui.Core.path))
    implementation(project(module.Feature.FoldersList.Data.path))
    implementation(project(module.Feature.FoldersList.Domain.path))
    implementation(project(module.Feature.FoldersList.Ui.Api.path))
    implementation(project(module.Feature.FoldersList.Ui.Core.path))
    implementation(project(module.Feature.HiddenNotesDialog.Data.path))
    implementation(project(module.Feature.HiddenNotesDialog.Domain.path))
    implementation(project(module.Feature.HiddenNotesDialog.Ui.Api.path))
    implementation(project(module.Feature.HiddenNotesDialog.Ui.Core.path))
    implementation(project(module.Feature.MarkerColorPicker.Data.path))
    implementation(project(module.Feature.MarkerColorPicker.Domain.path))
    implementation(project(module.Feature.MarkerColorPicker.Ui.Api.path))
    implementation(project(module.Feature.MarkerColorPicker.Ui.Core.path))
    implementation(project(module.Feature.NotesList.Data.path))
    implementation(project(module.Feature.NotesList.Domain.path))
    implementation(project(module.Feature.NotesList.Ui.Api.path))
    implementation(project(module.Feature.NotesList.Ui.Core.path))
    implementation(project(module.Feature.Onboarding.Data.path))
    implementation(project(module.Feature.Onboarding.Ui.Core.path))
    implementation(project(module.Feature.SearchBar.Ui.Api.path))
    implementation(project(module.Feature.SearchBar.Ui.Core.path))
    implementation(project(module.Feature.SortingSheet.Data.path))
    implementation(project(module.Feature.SortingSheet.Domain.path))
    implementation(project(module.Feature.SortingSheet.Ui.Api.path))
    implementation(project(module.Feature.SortingSheet.Ui.Core.path))
    implementation(project(module.Feature.TagsList.Data.path))
    implementation(project(module.Feature.TagsList.Domain.path))
    implementation(project(module.Feature.TagsList.Ui.Api.path))
    implementation(project(module.Feature.TagsList.Ui.Core.path))
    implementation(project(module.Feature.ThemePicker.Ui.Api.path))
    implementation(project(module.Feature.ThemePicker.Ui.Core.path))
    implementation(project(module.Feature.WallpaperPicker.Data.path))
    implementation(project(module.Feature.WallpaperPicker.Domain.path))
    implementation(project(module.Feature.WallpaperPicker.Ui.Api.path))
    implementation(project(module.Feature.WallpaperPicker.Ui.Core.path))
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.activity.compose)
    implementation(libs.compose.lifecycle)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.navigation)
    implementation(libs.splash.screen)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.compose.ui.test.junit4)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
    implementation(libs.koin.android)
}