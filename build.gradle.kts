import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jacksonVersion = "2.9.6"
val kluentVersion = "1.39"
val ktorVersion = "1.1.2"
val logstashLogbackEncoderVersion = "5.3"
val spekVersion = "2.0.0"

plugins {
    kotlin("jvm") version "1.3.21"
}

group = "no.nav.syfo"
version = "1.0-SNAPSHOT"

repositories {
    maven { url = uri("https://dl.bintray.com/kotlin/ktor") }
    maven { url = uri("https://dl.bintray.com/spekframework/spek-dev") }
    maven { url = uri("https://kotlin.bintray.com/kotlinx") }
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("net.logstash.logback:logstash-logback-encoder:$logstashLogbackEncoderVersion")

    testImplementation("org.amshove.kluent:kluent:$kluentVersion")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")

    testRuntimeOnly("org.spekframework.spek2:spek-runtime-jvm:$spekVersion")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks {
    "printVersion" {
        println(project.version)
    }
}
