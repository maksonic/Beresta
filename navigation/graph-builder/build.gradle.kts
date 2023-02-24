plugins {
    androidLibrary()
    kotlinAndroid()
}

android {
    namespace = BaseModule.Navigation.GraphBuilder.namespace
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
    implementation(project(BaseModule.Navigation.Router.path))
    implementation(project(BaseModule.Feature.SplashScreen.Api.path))
    implementation(project(BaseModule.Feature.Onboarding.Api.path))
    implementation(project(BaseModule.Screen.Main.path))
    implementation(project(BaseModule.Screen.Settings.path))
    implementation(project(BaseModule.Screen.Trash.path))
    implementation(Lib.Accompanist.navigation)
    implementation(Lib.Accompanist.systemUiController)
    implementation(Lib.Koin.compose)
}