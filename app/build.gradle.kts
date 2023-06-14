plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.apollographql.apollo3").version("3.7.3")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

apollo {
    service("service") {
        packageName.set("com.example")
    }
}

android {

    namespace = "com.example.radiofranceapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.radiofranceapp"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0")
    implementation("androidx.compose.material:material:1.2.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")


    //Apollo for API
    implementation("com.apollographql.apollo3:apollo-runtime:3.7.3")

    //Hilt for DI
    implementation("com.google.dagger:hilt-android:2.42")
    kapt("com.google.dagger:hilt-android-compiler:2.42")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Local unit tests
    testImplementation( "androidx.test:core:1.4.0")
    testImplementation("junit:junit:4.13.2" )
    testImplementation( "androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation( "com.google.truth:truth:1.1.3")
    testImplementation( "com.squareup.okhttp3:mockwebserver:4.9.1")
    testImplementation( "io.mockk:mockk:1.10.5")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.0")

    // Instrumentation tests
    androidTestImplementation( "com.google.dagger:hilt-android-testing:2.37")
    kaptAndroidTest ( "com.google.dagger:hilt-android-compiler:2.37")
    kaptAndroidTest ( "junit:junit:4.13.2")
    /*androidTestImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1"
    androidTestImplementation "androidx.arch.core:core-testing:2.1.0"
    androidTestImplementation "com.google.truth:truth:1.1.3"
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test:core-ktx:1.4.0'
    androidTestImplementation "com.squareup.okhttp3:mockwebserver:4.9.1"
    androidTestImplementation "io.mockk:mockk-android:1.10.5"
    androidTestImplementation 'androidx.test:runner:1.4.0'*/
}