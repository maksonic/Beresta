plugins {
    androidLibrary()
    kotlinAndroid()
    ksp()
}

android {
    namespace = BaseModule.Feature.NotesList.Data.namespace
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

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(BaseModule.Core.path))
    implementation(project(BaseModule.Feature.NotesList.Domain.path))
    implementation(project(BaseModule.BaseDomain.path))
    implementation(project(BaseModule.Data.Common.path))
    implementation(Lib.Room.ktx)
    ksp(Lib.Room.compiler)
    implementation(Lib.Koin.compose)
}