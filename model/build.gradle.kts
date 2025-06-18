plugins {
    // Apply the shared build logic from a convention plugin.
    // The shared code is located in `buildSrc/src/main/kotlin/kotlin-jvm.gradle.kts`.
    id("buildsrc.convention.kotlin-jvm")
    // Apply Kotlin Serialization plugin from `gradle/libs.versions.toml`.
    alias(libs.plugins.kotlinPluginSerialization)
    id("com.squareup.wire")
    alias(libs.plugins.protobuf)
}

wire {
    kotlin {
        // 可选：指定生成的包名
//        javaPackage = 'com.example.protobuf'

        // 可选：生成接口类
        includes = ["com.example.messages.*"]
    }
    sourcePath {
        srcDir += "${rootDir}/protos" // 你的 .proto 文件目录
    }

    // 可选：排除某些 proto 文件
    // prune 'google.protobuf.*'
}

dependencies {
    // Apply the kotlinx bundle of dependencies from the version catalog (`gradle/libs.versions.toml`).
    implementation(libs.bundles.kotlinxEcosystem)
    testImplementation(kotlin("test"))
    // 运行时库
    implementation("com.squareup.wire:wire-runtime:4.4.0")
    // 注解处理（生成代码）
    kapt("com.squareup.wire:wire-compiler:4.4.0")
//    annotationProcessor("com.squareup.wire:wire-compiler:4.4.0")
}