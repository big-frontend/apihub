import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.wire)

}
wire {
    kotlin {
//        javaPackage = "com.example.protobuf"
//        includes = listOf("com.example.messages.*")
        out = "src/main/kotlin"
    }
    sourcePath {
        srcDir("${rootDir}/proto")
    }
//    可选：排除某些 proto 文件
//    prune "google.protobuf.*"
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
    implementation(libs.wire.runtime)
    implementation(libs.wire.grpc)
    kapt(libs.wire.compiler)
//    annotationProcessor("com.squareup.wire:wire-compiler:4.4.0")
}