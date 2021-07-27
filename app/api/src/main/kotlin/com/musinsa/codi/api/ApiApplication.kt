package com.musinsa.codi.api

import com.musinsa.codi.BasePackage
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackageClasses = [BasePackage::class]
)
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(args = args) {
        setBannerMode(Banner.Mode.CONSOLE)
    }
}
