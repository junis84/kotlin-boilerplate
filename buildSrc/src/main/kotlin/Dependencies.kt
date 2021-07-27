object Libraries {
    private object Versions {
        const val reflect = "1.5.21"
        const val reflections = "0.9.12"
        const val kotlinLogging = "2.0.10"
        const val detektFormatting = "1.18.0-RC2"
        const val mariadbJavaClient = "3.0.0-alpha"
        const val hikariCP = "5.0.0"
        const val querydsl = "5.0.0.M1"
    }

    const val reflections = "org.reflections:reflections:${Versions.reflections}"
    const val kotlinLogging = "io.github.microutils:kotlin-logging-jvm:${Versions.kotlinLogging}"
    const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detektFormatting}"
    const val jackson = "com.fasterxml.jackson.module:jackson-module-kotlin"

    const val mariadbJavaClient = "org.mariadb.jdbc:mariadb-java-client:${Versions.mariadbJavaClient}"
    const val hikariCP = "com.zaxxer:HikariCP:${Versions.hikariCP}"
    const val querydslJpa = "com.querydsl:querydsl-jpa:${Versions.querydsl}"
    const val querydslApt = "com.querydsl:querydsl-apt:${Versions.querydsl}:jpa"

    object Kotlin {
        private const val version = "1.5.21"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
    }

    object Spring {
        const val starterWeb = "org.springframework.boot:spring-boot-starter-web"
        const val dataJpa = "org.springframework.boot:spring-boot-starter-data-jpa"
        const val devTools = "org.springframework.boot:spring-boot-devtools"
        const val configurationProcessor = "org.springframework.boot:spring-boot-configuration-processor"
    }
}

object TestLibraries {
    private object Versions {
        const val kotlinFaker = "1.7.1"
        const val mockk = "1.11.0"
        const val kotestRunnerJunit5 = "4.6.0"
        const val kotestAssertionsCore = "4.6.0"
        const val kotestProperty = "4.6.0"
    }

    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val kotestRunnerJunit5 = "io.kotest:kotest-runner-junit5:${Versions.kotestRunnerJunit5}"
    const val kotestAssertionsCore = "io.kotest:kotest-assertions-core:${Versions.kotestAssertionsCore}"
    const val kotestProperty = "io.kotest:kotest-property:${Versions.kotestProperty}"
    const val kotlinFaker = "io.github.serpro69:kotlin-faker:${Versions.kotlinFaker}"

    object Spring {
        const val starterTest = "org.springframework.boot:spring-boot-starter-test"
    }
}
