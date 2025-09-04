import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.wire)
}
kotlin {
    jvmToolchain(21)
}
wire {
    kotlin {
//        javaPackage = "com.example.protobuf"
//        includes = listOf("com.example.messages.*")
        out = "src/main/kotlin"
        // `client` to generate interfaces best suited to sending outbound calls.
        // `server` to generate interfaces best suited to receiving inbound calls.
        rpcRole = "server"
        // Server only
        // `suspending` to generate coroutines APIs that require a Kotlin coroutines context.
        // `blocking` to generate blocking APIs callable by Java and Kotlin.
        rpcCallStyle = "suspending"
        // Server only
        // True for emitted services to generate one interface per RPC.
        singleMethodServices = false
    }
    sourcePath {
        srcDir("${rootDir}/proto")
    }
    protoPath("${rootDir}/proto")
//    可选：排除某些 proto 文件
//    prune "google.protobuf.*"
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events(
            TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED
        )
    }
}

dependencies {
    implementation(project(":protoc-model"))
    implementation(project(":wire-model"))
    implementation(libs.bundles.kotlinxEcosystem)
    implementation(libs.wire.grpc)
}

application {
    mainClass = "com.electrolytej.vi.app.AppKt"
}
