plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = ModuleInfo.App.namespace
    compileSdk = AndroidConfig.COMPILE_SDK

    defaultConfig {
        applicationId = AndroidConfig.APP_ID
        minSdk = AndroidConfig.MIN_SDK
        targetSdk = AndroidConfig.TARGET_SDK
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME

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

    buildFeatures {
        viewBinding = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(ModuleInfo.Data.path))
    implementation(project(ModuleInfo.Common.Core.path))
    implementation(project(ModuleInfo.Common.Ui.path))
    implementation(project(ModuleInfo.Navigation.Graph.path))
    implementation(project(ModuleInfo.Navigation.Router.path))
    implementation(project(ModuleInfo.Feature.Auth.Data.path))
    implementation(project(ModuleInfo.Feature.Auth.Domain.path))
    implementation(project(ModuleInfo.Feature.Auth.Ui.path))
    implementation(project(ModuleInfo.Feature.Onboarding.Data.path))
    implementation(project(ModuleInfo.Feature.Onboarding.Domain.path))
    implementation(project(ModuleInfo.Feature.Onboarding.Ui.path))
    implementation(project(ModuleInfo.Feature.Payments.path))
    implementation(libs.appcompat)
    implementation(libs.core.ktx)
    implementation(libs.datastore)
    implementation(libs.glide)
    implementation(libs.material)
    implementation(libs.navigation.fragment)
    implementation(libs.splash.screen)
    implementation(libs.koin.android)
}