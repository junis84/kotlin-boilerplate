plugins {
    kotlin("jvm")

    kotlin("plugin.allopen")
    kotlin("plugin.jpa")
    kotlin("kapt")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

dependencies {
    implementation(Libraries.querydslJpa)
    kapt(Libraries.querydslApt)
}
