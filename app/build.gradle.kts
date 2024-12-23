plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "ali.company.camdetector"
    compileSdk = 34

    defaultConfig {
        applicationId = "ali.company.camdetector"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    //image classification
    implementation("com.google.mlkit:image-labeling:17.0.9")
    //custom image classification
    implementation("com.google.mlkit:image-labeling-custom:17.0.3")
    //object detection
    implementation("com.google.mlkit:object-detection:17.0.2")
    //face detection
    implementation("com.google.mlkit:face-detection:16.1.7")

    //audio
    implementation("org.tensorflow:tensorflow-lite-task-audio:0.4.4")
    //text
    implementation("org.tensorflow:tensorflow-lite-task-text:0.4.4")
    implementation(libs.camera.core)

    // androidx camera
    implementation ("androidx.camera:camera-core:1.1.0")
    implementation ("androidx.camera:camera-view:1.1.0")
    implementation ("androidx.camera:camera-camera2:1.1.0")
    implementation ("androidx.camera:camera-lifecycle:1.1.0")

    // pose
    // If you want to use the base sdk
    implementation ("com.google.mlkit:pose-detection:18.0.0-beta3")
    // If you want to use the accurate sdk
    implementation ("com.google.mlkit:pose-detection-accurate:18.0.0-beta3")

    implementation ("org.tensorflow:tensorflow-lite:2.13.0")
    implementation ("org.tensorflow:tensorflow-lite-gpu:2.13.0")
    implementation ("org.tensorflow:tensorflow-lite-support:0.4.0")
    implementation(libs.guava)


    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}