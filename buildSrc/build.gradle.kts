plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    //https://mvnrepository.com/artifact/com.android.tools.build/gradle?repo=google
    implementation("com.android.tools.build:gradle:7.1.1")

    //Must match kotlinVersion from dependencies.kt
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")

    implementation("com.google.dagger:hilt-android-gradle-plugin:2.38.1")

//    //URL: https://developers.google.com/android/guides/google-services-plugin
//    implementation("com.google.gms:google-services:4.3.10")
//
//    implementation("com.google.firebase:firebase-crashlytics-gradle:2.8.1")
}