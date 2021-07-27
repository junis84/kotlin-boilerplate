plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
}

object Dependencies {
    private object Versions {
        const val kotlin = "1.5.21"
        const val detekt = "1.18.0-RC2"
        const val testSets = "4.0.0"
        const val springBoot = "2.5.2"
        const val springDependency = "1.0.11.RELEASE"
    }

    val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlinNoArg = "org.jetbrains.kotlin:kotlin-noarg:${Versions.kotlin}"
    val kotlinAllopen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"

    val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detekt}"
    val testSets = "org.unbroken-dome.gradle-plugins:gradle-testsets-plugin:${Versions.testSets}"
    val springBoot = "org.springframework.boot:spring-boot-gradle-plugin:${Versions.springBoot}"
    val springDependency = "io.spring.gradle:dependency-management-plugin:${Versions.springDependency}"
}

dependencies {
    implementation(Dependencies.kotlin)
    implementation(Dependencies.kotlinNoArg)
    implementation(Dependencies.kotlinAllopen)

    implementation(Dependencies.detekt)
    implementation(Dependencies.testSets)
    implementation(Dependencies.springBoot)
    implementation(Dependencies.springDependency)
}
