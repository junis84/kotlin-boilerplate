import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    jacoco
    id("io.gitlab.arturbosch.detekt")
    id("org.unbroken-dome.test-sets")
}

group = "com.musinsa"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config = files("$rootDir/detekt.yml")

    reports {
        html.enabled = true
        xml.enabled = false
        txt.enabled = false
        sarif.enabled = false
    }
}

tasks {
    withType<KotlinCompile>() {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
            allWarningsAsErrors = true
        }
    }

    withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        this.jvmTarget = "11"
    }
}

dependencies {
    implementation(Libraries.Kotlin.stdlib)
    implementation(Libraries.Kotlin.reflect)
    implementation(Libraries.kotlinLogging)
    detektPlugins(Libraries.detektFormatting)

    testImplementation(TestLibraries.mockk)
    testImplementation(TestLibraries.kotestRunnerJunit5)
    testImplementation(TestLibraries.kotestAssertionsCore)
    testImplementation(TestLibraries.kotestProperty)
    testImplementation(TestLibraries.kotlinFaker)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }

    test {
        finalizedBy("jacocoTestReport")
    }

    jacocoTestReport {
        reports {
            html.required.set(true)
            xml.required.set(false)
            csv.required.set(false)
        }

        finalizedBy("jacocoTestCoverageVerification")
    }

    jacocoTestCoverageVerification {
        violationRules {
            rule {
                // 'element'가 없으면 프로젝트의 전체 파일을 합친 값을 기준으로 한다.
                limit {
                    // 'counter'를 지정하지 않으면 default는 'INSTRUCTION'
                    // 'value'를 지정하지 않으면 default는 'COVEREDRATIO'
                    minimum = "0.30".toBigDecimal()
                }
            }

            rule {
                // 룰을 간단히 켜고 끌 수 있다.
                enabled = true

                // 룰을 체크할 단위는 클래스 단위
                element = "CLASS"

                // 브랜치 커버리지를 최소한 90% 만족시켜야 한다.
                limit {
                    counter = "BRANCH"
                    value = "COVEREDRATIO"
                    minimum = "0.90".toBigDecimal()
                }

                // 라인 커버리지를 최소한 80% 만족시켜야 한다.
                limit {
                    counter = "LINE"
                    value = "COVEREDRATIO"
                    minimum = "0.80".toBigDecimal()
                }

                // 빈 줄을 제외한 코드의 라인수를 최대 200라인으로 제한한다.
                limit {
                    counter = "LINE"
                    value = "TOTALCOUNT"
                    maximum = "200".toBigDecimal()
                }

                // 커버리지 체크를 제외할 디렉토리
                classDirectories.setFrom(sourceSets.main.get().output.asFileTree.matching {
                    exclude(
                        "**/valueobject/*"
                    )
                })

                // 커버리지 체크를 제외할 클래스들
                excludes = listOf(
                    "com.musinsa.shared.domain.aggregate.DomainException",
                    "com.musinsa.codi.BasePackage"
                )
            }
        }
    }
}

testSets {
    "integrationTest" { dirName = "integration-test" }
}
