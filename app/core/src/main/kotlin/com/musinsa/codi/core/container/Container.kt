package com.musinsa.codi.core.container

import com.musinsa.shared.application.container.Container
import kotlin.reflect.KClass
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class Container(private val context: ApplicationContext) : Container {
    override fun <T : Any> get(dependency: KClass<T>): T? {
        return context.getBean(dependency.java)
    }
}
