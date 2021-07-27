plugins {
    kotlin("jvm")

    id("org.springframework.boot")
    id("io.spring.dependency-management")

    kotlin("plugin.spring")
    kotlin("kapt")
}

dependencies {
    implementation(Libraries.jackson)
    kapt(Libraries.Spring.configurationProcessor)
    developmentOnly(Libraries.Spring.devTools)
}
