package com.musinsa.shared.application.container

import kotlin.reflect.KClass

interface Container {
    fun <T : Any> get(dependency: KClass<T>): T?
}
