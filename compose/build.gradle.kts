import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

tasks.named("check").configure { dependsOn("compileDebugAndroidTestJavaWithJavac") }

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release").configure {
            isMinifyEnabled = false
            consumerProguardFiles("proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            useIR = true
        }
    }

    composeOptions {
        kotlinCompilerVersion = "1.4.20"
        kotlinCompilerExtensionVersion = "1.0.0-alpha08"
    }
}


dependencies {
    implementation(platform(Config.Libs.Firebase.bom))
    api(project(":common"))
    api(Config.Libs.Firebase.database)

    api("androidx.compose.ui:ui:1.0.0-alpha08")
    // Tooling support (Previews, etc.)
    api("androidx.compose.ui:ui-tooling:1.0.0-alpha08")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    api("androidx.compose.foundation:foundation:1.0.0-alpha08")
    // Material Design
    api("androidx.compose.material:material:1.0.0-alpha08")
    // Material design icons
    api("androidx.compose.material:material-icons-core:1.0.0-alpha08")
    api("androidx.compose.material:material-icons-extended:1.0.0-alpha08")
    // Integration with observables
    api("androidx.compose.runtime:runtime-livedata:1.0.0-alpha08")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.0-alpha08")

    implementation("androidx.paging:paging-runtime:3.0.0-alpha11")
    testImplementation("androidx.paging:paging-common:3.0.0-alpha11")
    implementation("androidx.paging:paging-compose:1.0.0-alpha04")
    annotationProcessor(Config.Libs.Androidx.lifecycleCompiler)

    androidTestImplementation(Config.Libs.Test.junit)
    androidTestImplementation(Config.Libs.Test.junitExt)
    androidTestImplementation(Config.Libs.Test.runner)
    androidTestImplementation(Config.Libs.Test.rules)
}

apply {
    plugin("kotlin-android")
}
repositories {
    mavenCentral()
}