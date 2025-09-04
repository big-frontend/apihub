import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
}
kotlin {
    jvmToolchain(21)
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
