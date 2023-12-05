// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
    }
}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}