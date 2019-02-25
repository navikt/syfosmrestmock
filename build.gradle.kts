import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jacksonVersion = "2.9.6"
val kluentVersion = "1.48"
val ktorVersion = "1.1.2"
val logbackVersion = "1.2.3"
val logstashLogbackEncoderVersion = "5.3"
val spekVersion = "2.0.0"

plugins {
    kotlin("jvm") version "1.3.21"
    id("com.github.johnrengelman.shadow") version "4.0.4"
}

group = "no.nav.syfo"
version = "1.0"

repositories {
    jcenter()
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

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("net.logstash.logback:logstash-logback-encoder:$logstashLogbackEncoderVersion")

    testImplementation("org.amshove.kluent:kluent:$kluentVersion")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")

    testRuntimeOnly("org.spekframework.spek2:spek-runtime-jvm:$spekVersion")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
}

tasks.withType<Jar> {
    manifest.attributes["Main-Class"] = "no.nav.syfo.restmock.BootstrapKt"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks {
    register("printVersion") {
        println(project.version)
    }
}
