plugins {
    androidLibrary()
    kotlinAndroid()
}

android {
    namespace = BaseModule.Data.Notes.namespace
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(BaseModule.Core.path))
    implementation(project(BaseModule.Feature.NotesList.Api.path))
    implementation(project(BaseModule.Data.Common.path))
    implementation(project(BaseModule.Data.Database.path))
    implementation(Lib.Koin.compose)
    implementation(Lib.Room.ktx)
}