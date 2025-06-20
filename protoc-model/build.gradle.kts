import com.google.protobuf.gradle.GenerateProtoTask
import com.google.protobuf.gradle.remove
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
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.12" // 编译器版本
    }
    generateProtoTasks {

        all().forEach { task ->

            task.builtins.names.forEach { name ->
                task.builtins.remove(name)
            }
            task.builtins.removeAll { _: GenerateProtoTask.PluginOptions -> true }
            task.builtins.create("java") {
                println("cjf ${generatedFilesBaseDir}")
                generatedFilesBaseDir = "${projectDir}/src/main"
//                outputSubDir = "java"
            }

            // gRPC 代码生成（作为插件）
//            task.plugins.create("grpc") {
//                outputSubDir = "src/generated/main/grpc"
//                option("lite")
//            }
        }
    }
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
    testImplementation(kotlin("test"))
    implementation("com.google.protobuf:protobuf-java:4.28.2")
}
