plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.pdm.storage.datastore"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                val java by registering { option("lite") }
                val kotlin by registering { option("lite") }
            }
        }
    }
}

dependencies {
    api(libs.androidx.datastore.core)
    api(libs.androidx.datastore)
    api(libs.androidx.core.ktx)
    api(platform(libs.koin.bom))
    api(libs.koin.android)
    api(libs.koin.core)
    api(libs.protobuf.kotlin.lite)
    api(projects.common.model)
}
