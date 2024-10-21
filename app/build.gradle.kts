plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.chavez.patrick.orientatecproyecto"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.chavez.patrick.orientatecproyecto"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Dependencias de AndroidX
    implementation(libs.androidx.core.ktx) // Biblioteca de utilidades de Android
    implementation(libs.androidx.appcompat) // Biblioteca de AppCompat para compatibilidad
    implementation(libs.material) // Biblioteca de Material Design
    implementation(libs.androidx.activity) // Biblioteca de actividades de AndroidX
    implementation(libs.androidx.constraintlayout) // Biblioteca para ConstraintLayout
    implementation(libs.play.services.maps) // Servicios de Google Maps

    // Google Play Services Auth para inicio de sesión con Google
    implementation("com.google.android.gms:play-services-auth:20.5.0")

    // Dependencias para pruebas
    testImplementation(libs.junit) // Dependencia de JUnit para pruebas unitarias
    androidTestImplementation(libs.androidx.junit) // Dependencia de JUnit para pruebas de UI
    androidTestImplementation(libs.androidx.espresso.core) // Biblioteca de Espresso para pruebas de UI
}
