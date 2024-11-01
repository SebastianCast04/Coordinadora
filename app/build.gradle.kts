plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinxSerialization)
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.googleService)

}

android {
    namespace = "com.example.coordinadoraapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.coordinadoraapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)
    implementation(libs.kotlinx.serialization.json)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.7.4")
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation ("androidx.arch.core:core-testing:2.1.0")
    testImplementation ("app.cash.turbine:turbine:1.0.0")

    // Mockito para pruebas unitarias
    testImplementation ("org.mockito:mockito-core:5.14.1")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:5.4.0")

    // Coroutines Test para pruebas unitarias de coroutines
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    implementation("androidx.hilt:hilt-navigation-fragment:1.2.0")

    // Room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Volley
    implementation(libs.volley)

    // Firebase
    implementation(platform(libs.firebase.bom))
    //implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)

    // Google Maps
    implementation(libs.play.services.maps)
    implementation ("com.google.maps.android:maps-compose:2.5.0")

    // Hilt ViewModel
    kapt(libs.androidx.hilt.compiler)
    //implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")

    // Voyager for navigation
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.transitions)
    implementation(libs.voyager.tabNavigator)
    implementation(libs.voyager.koin)

    // Compose
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)

    //Para navegación
    implementation("cafe.adriel.voyager:voyager-hilt:1.1.0-beta02")
}

kapt {
    correctErrorTypes = true
}



