import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.protobuf)

}
sourceSets {
    main {
        proto {
            srcDir("${rootDir}/proto")
        }
    }
}
protobuf {
    generatedFilesBaseDir = "src/main/kotlin" // 基础目录
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.12" // 编译器版本
    }
}
kotlin {
    jvmToolchain(21)
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events(
            TestLogEvent.FAILED,
            TestLogEvent.PASSED,
            TestLogEvent.SKIPPED
        )
    }
}
dependencies {
    testImplementation(kotlin("test"))
    implementation("com.google.protobuf:protobuf-java:4.28.2")
}
