plugins {
    id("com.musinsa.spring-conventions")
    id("com.musinsa.jpa-conventions")
}

dependencies {
    implementation(Libraries.mariadbJavaClient)
    implementation(Libraries.hikariCP)
    implementation(Libraries.Spring.dataJpa)
}
