plugins {
    androidLibrary()
    kotlinAndroid()
    ksp()
}

android {
    namespace = BaseModule.Feature.TasksList.Data.namespace
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
    implementation(project(BaseModule.Feature.TasksList.Domain.path))
    implementation(Lib.Room.runtime)
    ksp(Lib.Room.compiler)
    implementation(Lib.Koin.compose)
}