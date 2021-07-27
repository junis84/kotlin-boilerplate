package com.musinsa.shared.domain.valueobject

@Suppress("UnnecessaryAbstractClass")
abstract class ListValueObject<E>(value: List<E>) :
    ValueObject<List<E>>(value), Iterable<E> {

    override fun iterator(): Iterator<E> {
        return value.iterator()
    }
}
