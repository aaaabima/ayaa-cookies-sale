plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  id("kotlin-parcelize")
  id("kotlin-kapt")
}

android {
  namespace = "id.aaaabima.ayaacookiessale"
  compileSdk = 34

  defaultConfig {
    applicationId = "id.aaaabima.ayaacookiessale"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    viewBinding = true
  }
}

dependencies {

  // Room components
  implementation(libs.androidx.room.runtime)
  implementation(libs.androidx.activity)
  implementation(libs.androidx.room.ktx)
  kapt("androidx.room:room-compiler:2.6.1")
  annotationProcessor(libs.androidx.room.compiler)
  androidTestImplementation(libs.androidx.room.testing)

  // Lifecycle components
  implementation(libs.androidx.lifecycle.viewmodel.ktx)
  implementation(libs.androidx.lifecycle.livedata.ktx)
  implementation(libs.androidx.lifecycle.common)

  // Kotlin components
  implementation(libs.kotlin.stdlib.jdk7)
  api(libs.kotlinx.coroutines.core)
  api(libs.kotlinx.coroutines.android)

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.constraintlayout)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}