package com.musinsa.shared.domain.valueobject

@Suppress("UnnecessaryAbstractClass")
abstract class ValueObject<T>(val value: T) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ValueObject<*>

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return "${javaClass.simpleName}(value=$value)"
    }
}

fun ValueObject<Boolean>.isTrue(): Boolean {
    return value
}

fun ValueObject<Boolean>.isFalse(): Boolean {
    return !value
}
