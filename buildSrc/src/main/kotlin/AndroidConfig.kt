import org.gradle.api.JavaVersion


object AndroidConfig {

    const val compileSdk = 33
    const val minSdk = 21
    const val targetSdk = 33
    const val kotlinJvmTarget = "1.8"

    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8

}
