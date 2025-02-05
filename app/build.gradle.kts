plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.meutreino"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.meutreino"
        minSdk = 28
        targetSdk = 35
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
    // Dependências principais
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Dependências do RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.2.1") // Adicionada a dependência do RecyclerView

    // Dependências do Room (Banco de dados)
    implementation(libs.room.common)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)

    // Testes
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
