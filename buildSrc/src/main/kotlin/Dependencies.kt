object Dependencies {


    object Android {
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.Android.APPCOMPAT}"
        const val CORE = "androidx.core:core-ktx:${Versions.Android.CORE}"
        const val LIFECYCLE_RUNTIME =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.LIFECYCLE}"
        const val MATERIAL = "com.google.android.material:material:${Versions.Android.MATERIAL}"

        const val NAVIGATION_FRAGMENT =
            "androidx.navigation:navigation-fragment-ktx:${Versions.Android.NAVIGATION}"
        const val NAVIGATION_UI =
            "androidx.navigation:navigation-ui-ktx:${Versions.Android.NAVIGATION}"

    }


    object Test {
        const val JUNIT = "junit:junit:${Versions.TEST.JUNIT}"
        const val CORE = "androidx.test:core-ktx:${Versions.TEST.CORE}"
        const val COROUTINES =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.TEST.COROUTINES}"
    }


    object Kotlin {
        const val COROUTINES =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.COROUTINES}"
        const val COROUTINES_PLAY_SERVICES =
            "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.Kotlin.COROUTINES_PLAY_SERVICES}"
    }

    object Square {
        const val HTTP_LOGGING_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:${Versions.Square.OKHTTP}"
        const val MOSHI_CONVERTER =
            "com.squareup.retrofit2:converter-moshi:${Versions.Square.RETROFIT}"
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.Square.RETROFIT}"
        const val GSON = "com.squareup.retrofit2:converter-gson:${Versions.Square.RETROFIT}"
    }

    object Koin {
        const val CORE = "io.insert-koin:koin-core:${Versions.Koin.KOIN_VERSION}"
        const val KOIN_ANDROID = "io.insert-koin:koin-android:${Versions.Koin.KOIN_VERSION}"
        const val KOIN_VIEW_MODEL =
            "io.insert-koin:koin-android-viewmodel:${Versions.Koin.KOIN_VIEW_MODEL_VERSION}"
    }
}
