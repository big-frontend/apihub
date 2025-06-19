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
    generatedFilesBaseDir = "src" // 基础目录
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.12" // 编译器版本
    }
//    generateProtoTasks {
//
//        all().forEach { task ->
//            // Java 类生成
//            task.builtins.register("java") {
//                outputSubDir = "src/main/kotlin"
//            }
//
//            // gRPC 代码生成（作为插件）
//            task.plugins.create("grpc") {
//                outputSubDir = "src/generated/main/grpc"
//            }
//        }
//    }
//    generateProtoTasks {
//        all().forEach { task ->
//            // 配置 Java 生成器
//            task.builtins {
//
//                create("java") {
//                    // 设置新的输出目录（相对于项目根目录）
//                    outputSubDir = "src/main/java"
//                }
//                // 配置 gRPC 生成器（可选）
////                create("grpc") {
////                    outputSubDir = "src/main/grpc"
////                    option("lite")
////                }
//            }
//        }
//    }
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
