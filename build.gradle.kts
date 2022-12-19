buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.TOOLS_GRADLE)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.21")
    }
}


task<Delete>("clean") {
    delete(rootProject.buildDir)
}