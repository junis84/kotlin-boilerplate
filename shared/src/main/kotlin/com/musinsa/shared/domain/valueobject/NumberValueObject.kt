package com.musinsa.shared.domain.valueobject

@Suppress("UnnecessaryAbstractClass")
abstract class NumberValueObject<T : Number>(value: T) :
    ValueObject<T>(value), Comparable<NumberValueObject<T>> {

    override fun compareTo(other: NumberValueObject<T>): Int {
        return when (value) {
            is Byte -> value.compareTo(other.value as Byte)
            is Int -> value.compareTo(other.value as Int)
            is Long -> value.compareTo(other.value as Long)
            is Float -> value.compareTo(other.value as Float)
            is Double -> value.compareTo(other.value as Double)
            else -> throw UnsupportedOperationException()
        }
    }
}
