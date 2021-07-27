import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("com.musinsa.kotlin-conventions")
    id("com.musinsa.spring-data-conventions")
}

val bootJar: BootJar by tasks
val jar: Jar by tasks

bootJar.enabled = false
jar.enabled = true
