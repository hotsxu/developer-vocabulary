import com.android.build.gradle.AppExtension
import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("kapt")
    kotlin("android")
    kotlin("android.extensions")
}

configure<AppExtension> {
    compileSdkVersion(28)
    buildToolsVersion("28.0.3")
    defaultConfig {
        applicationId = "com.hotsx.vocabulary"
        minSdkVersion(23)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    val kapt by configurations
    val implementation by configurations
    val testImplementation by configurations
    val androidTestImplementation by configurations

    /**Test*/
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.0")
    /**Commonly*/
    implementation(fileTree(mapOf("dir" to "libs", "include" to "*.jar")))
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("android.arch.navigation:navigation-fragment:1.0.0-alpha07")
    implementation("android.arch.navigation:navigation-ui:1.0.0-alpha07")
    implementation("android.arch.navigation:navigation-fragment-ktx:1.0.0-alpha07")
    implementation("android.arch.navigation:navigation-ui-ktx:1.0.0-alpha07")
    implementation("androidx.core:core-ktx:1.0.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.google.android.material:material:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("com.android.support:recyclerview-v7:28.0.0")
    /**Room*/
    kapt("androidx.room:room-compiler:2.1.0-alpha03")
    implementation("androidx.room:room-runtime:2.1.0-alpha03")
    /**Retrofit*/
    implementation("com.squareup.retrofit2:retrofit:2.5.0")
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")
    /**Gson*/
    implementation("com.google.code.gson:gson:2.8.5")
    /**Kotlin Coroutines*/
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.0.1")
}