plugins {
    id("com.musinsa.application-conventions")
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":context:codi"))
    implementation(project(":app:core"))
    implementation(Libraries.Spring.starterWeb)

    testImplementation(TestLibraries.Spring.starterTest)
}
