plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = ModuleInfo.Data.namespace
    compileSdk = AndroidConfig.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfig.MIN_SDK

        testInstrumentationRunner = AndroidConfig.TEST_RUNNER
    }

    buildTypes {
        release {
            isMinifyEnabled = AndroidConfig.IS_MINIFY
            isShrinkResources = AndroidConfig.IS_SHRINK_RESOURCES

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = AndroidConfig.JAVA_VERSION
        targetCompatibility = AndroidConfig.JAVA_VERSION
    }

    kotlinOptions {
        jvmTarget = AndroidConfig.JVM_TARGET
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {
    implementation(project(ModuleInfo.Common.Core.path))
    implementation(project(ModuleInfo.Feature.Auth.Domain.path))
    implementation(libs.gson)
    implementation(libs.koin.android)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.retrofit.interceptor)
}
