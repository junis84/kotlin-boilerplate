import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("com.musinsa.application-conventions")
    id("com.musinsa.spring-data-conventions")
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":context:codi"))
}

val bootJar: BootJar by tasks
val jar: Jar by tasks

bootJar.enabled = false
jar.enabled = true
