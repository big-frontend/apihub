import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.wire)
//    alias(libs.plugins.modulepublisher)
}
wire {
    kotlin {
//        javaPackage = "com.example.protobuf"
//        includes = listOf("com.example.messages.*")
        out = "src/main/kotlin"
        // `client` to generate interfaces best suited to sending outbound calls.
        // `server` to generate interfaces best suited to receiving inbound calls.
        rpcRole = "client"
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
//    protoPath("${rootDir}/proto")
//    可选：排除某些 proto 文件
//    prune "google.protobuf.*"
}
//publish {
//    it.name = "wire-model"
//    groupId = "io.github.electrolytej"
//    artifactId = "wiremodel"
//    it.version = "1.0.0"
//    website = "https://github.com/big-frontend/module-assembler"
//}
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
tasks.register<Exec>("generateSwiftWireProtos") {
    description = "Generates Swift code from proto files using Wire Compiler"
    group = "wire"
    workingDir = project.rootDir
    commandLine = listOf(
        "java",
        "-jar",
        "${projectDir}/wire-compiler-5.4.0.jar",
        "--proto_path=${rootDir}/proto",  // 替换为实际的 proto 文件目录
        "--swift_out=$projectDir/src/main/swift",  // 替换为生成文件的输出目录
//        "--experimental-module-manifest=$projectDir/manifest.yaml"  // 替换为 manifest 文件路径
    )
    // 可选：设置环境变量（如果需要）
//    environment("JAVA_HOME", System.getenv("JAVA_HOME"))
    // 可选：指定任务依赖（例如在编译前执行）
//    dependsOn("assembleDebug")
    // 可选：设置任务输出目录，让 Gradle 知道哪些文件是该任务生成的
    outputs.dir("$projectDir/src/main/swift")  // 与 --swift_out 保持一致
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(libs.wire.runtime)
    implementation(libs.wire.grpc)
    kapt(libs.wire.compiler)
//    annotationProcessor("com.squareup.wire:wire-compiler:4.4.0")
//    implementation("io.outfoxx:swiftpoet:1.6.6")
    //restful
    api("io.github.jamesfchen:retrofit:1.0.0")
//    api "com.squareup.retrofit2:adapter-rxjava3:2.11.0"
    api("com.squareup.retrofit2:converter-wire:2.11.0") {
        exclude(group = "com.squareup.retrofit2", module = "retrofit")
    }
    api("com.squareup.retrofit2:converter-gson:2.11.0") {
        exclude(group = "com.squareup.retrofit2", module = "retrofit")
    }
    api("com.squareup.retrofit2:converter-moshi:2.11.0") {
        exclude(group = "com.squareup.retrofit2", module = "retrofit")
    }
}