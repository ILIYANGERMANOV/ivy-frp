import com.ivy.frp.buildsrc.Project
import com.ivy.frp.buildsrc.frpModuleDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")

    id("maven-publish")
}

android {
    //------------------ Android Library Publish ------------------------
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
    //------------------ Android Library Publish ------------------------

    compileSdk = Project.compileSdkVersion

    defaultConfig {
        minSdk = Project.minSdk
        targetSdk = Project.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        //Exclude this files so Jetpack Compose UI tests can build
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
        //-------------------------------------------------------
    }

    //Compose -----------------------------------------------------------------
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = com.ivy.frp.buildsrc.GlobalVersions.compose
    }
    //Compose -----------------------------------------------------------------
}

//------------------ Android Library Publish ------------------------
publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = Project.Lib.groupId
            artifactId = Project.Lib.artifactId
            version = Project.Lib.version

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
//------------------ Android Library Publish ------------------------

dependencies {
    frpModuleDependencies()
}