package com.musinsa.codi.faker

import kotlin.random.Random

internal inline fun <reified T : Enum<T>> randomEnum(): T {
    val size = enumValues<T>().size
    val randomOrdinal = Random.nextInt(0, size)

    return enumValues<T>()[randomOrdinal]
}
