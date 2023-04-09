plugins {
    androidLibrary()
    kotlinAndroid()
    ksp()
}

android {
    namespace = BaseModule.Data.Database.namespace
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
    implementation(project(BaseModule.Data.Common.path))
    implementation(Lib.Room.ktx)
    ksp(Lib.Room.compiler)
    implementation(Lib.Koin.compose)

    //Test
    implementation(Lib.Test.core)
    testImplementation(Lib.Test.junit)
    androidTestImplementation(Lib.Test.junitExt)
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation ("com.google.truth:truth:1.1.3")
    androidTestImplementation ("com.google.truth.extensions:truth-java8-extension:1.1.3")
}