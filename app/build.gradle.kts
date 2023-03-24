plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}
android {
    namespace = "com.android.countryandstate"
    viewBinding.enable = true

    defaultConfig.apply {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        compileSdk = AndroidConfig.compileSdk
        applicationId = "com.android.countryandstate"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = AndroidConfig.sourceCompatibility
        targetCompatibility = AndroidConfig.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = AndroidConfig.kotlinJvmTarget
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    api(Dependencies.Android.CORE)
    api(Dependencies.Android.APPCOMPAT)
    api(Dependencies.Android.MATERIAL)
    api(Dependencies.Test.JUNIT)
    api(Dependencies.Square.RETROFIT)
    api(Dependencies.Square.MOSHI_CONVERTER)
    api(Dependencies.Square.HTTP_LOGGING_INTERCEPTOR)
    api(Dependencies.Square.GSON)
    api(Dependencies.Kotlin.COROUTINES)
    api(Dependencies.Android.LIFECYCLE_RUNTIME)

    api(Dependencies.Android.NAVIGATION_UI)
    api(Dependencies.Android.NAVIGATION_FRAGMENT)

    api(Dependencies.Koin.CORE)
    api(Dependencies.Koin.KOIN_ANDROID)
    api(Dependencies.Koin.KOIN_VIEW_MODEL)

}
